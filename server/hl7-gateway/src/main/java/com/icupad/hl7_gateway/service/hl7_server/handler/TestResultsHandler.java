package com.icupad.hl7_gateway.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.group.ORU_R01_ORDER_OBSERVATION;
import ca.uhn.hl7v2.model.v23.group.ORU_R01_PATIENT;
import ca.uhn.hl7v2.model.v23.message.ORU_R01;
import ca.uhn.hl7v2.model.v23.segment.PID;
import ca.uhn.hl7v2.model.v23.segment.PV1;
import com.icupad.hl7_gateway.domain.*;
import com.icupad.hl7_gateway.service.RawTestNameService;
import com.icupad.hl7_gateway.service.StayService;
import com.icupad.hl7_gateway.service.TestRequestService;
import com.icupad.hl7_gateway.service.TestResultService;
import com.icupad.hl7_gateway.service.hl7_server.MissingTestNameMappingException;
import com.icupad.hl7_gateway.service.hl7_server.RegisterPatient;
import com.icupad.hl7_gateway.service.hl7_server.segment_parser.OBRParser;
import com.icupad.hl7_gateway.service.hl7_server.segment_parser.OBXParser;
import com.icupad.hl7_gateway.service.hl7_server.segment_parser.PV1Parser;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestResultsHandler implements MessageHandler<ORU_R01> {
    private final RegisterPatient registerPatient;
    private final StayService stayService;
    private final PV1Parser pv1Parser;
    private final OBRParser obrParser;
    private final OBXParser obxParser;
    private final TestRequestService testRequestService;
    private final TestResultService testResultService;
    private final RawTestNameService rawTestNameService;

    @Autowired
    public TestResultsHandler(RegisterPatient registerPatient,
                              StayService stayService,
                              TestRequestService testRequestService,
                              TestResultService testResultService,
                              PV1Parser pv1Parser,
                              OBRParser obrParser,
                              OBXParser obxParser,
                              RawTestNameService rawTestNameService) {
        this.registerPatient = registerPatient;
        this.stayService = stayService;
        this.testRequestService = testRequestService;
        this.testResultService = testResultService;
        this.pv1Parser = pv1Parser;
        this.obrParser = obrParser;
        this.obxParser = obxParser;
        this.rawTestNameService = rawTestNameService;
    }

    /**
     * Analyses ORU^R01 message
     * <p>
     * First step is to check if patient is registered. If not, registration is executed.
     * <p>
     * Then test results are mapped to desired domain models.
     * <p>
     * A situation when only partially results are send may occur. Later, in another message, the same results are send
     * including previously missing results. In that case method prevents test results duplication.
     *
     * @param oru_r01 message with test results
     * @throws HL7Exception
     * @throws MissingTestNameMappingException To normalize hl7 data structures to desired domain models,
     *                                         the mappings between raw test names and proper tests name are required.
     *                                         The method detects missing mappings and throws exception.
     */
    @Override
    @Transactional
    public void handle(ORU_R01 oru_r01) throws HL7Exception {
        if (!isPatientRegistered(oru_r01)) {
            registerPatient.accept(getPID(oru_r01), getPV1(oru_r01));
        }

        handleResults(oru_r01);
    }

    @Override
    public Class<ORU_R01> getMessageType() {
        return ORU_R01.class;
    }

    private void handleResults(ORU_R01 oru_r01) throws HL7Exception {
        List<Pair<TestRequest, TestResult>> testsRequestsAndResults = parseOBRAndOBX(oru_r01);
        List<Pair<TestRequest, TestResult>> filteredTestsRequestsAndResults =
                filterOutIrrelevantData(testsRequestsAndResults);

        handleNewRawTestNames(filteredTestsRequestsAndResults);
        handleTestRequests(filteredTestsRequestsAndResults);
        handleTestResults(getStay(oru_r01), filteredTestsRequestsAndResults);
    }

    private void handleTestResults(Stay stay, List<Pair<TestRequest, TestResult>> filteredTestsRequestsAndResults)
            throws HL7Exception {
        filteredTestsRequestsAndResults.stream()
                .map(this::mergeTestRequestWithDB)
                .map(this::associateTestResultWithTestRequest)
                .map(testResult -> associateTestResultWithStay(testResult, stay))
                .forEach(testResultService::save);
    }

    private void handleTestRequests(List<Pair<TestRequest, TestResult>> filteredTestsRequestsAndResults) {
        filteredTestsRequestsAndResults.stream()
                .map(Pair::getLeft)
                .map(this::associateTestRequestWithTest)
                .forEach(testRequestService::save);
    }

    private void handleNewRawTestNames(List<Pair<TestRequest, TestResult>> testsRequestsAndResults) {
        List<RawTestName> rawTestNames = testsRequestsAndResults.stream()
                .map(Pair::getLeft)
                .map(TestRequest::getRawTestName)
                .filter(this::ifRawTestNameAssociatedWithTestNotExists)
                .map(this::createRawTestName)
                .collect(Collectors.toList());

        if (!rawTestNames.isEmpty()) {
            throw new MissingTestNameMappingException(rawTestNames);
        }
    }

    private RawTestName createRawTestName(String rawTestNameStr) {
        RawTestName rawTestName = new RawTestName();
        rawTestName.setRawName(rawTestNameStr);
        return rawTestName;
    }

    private boolean ifRawTestNameAssociatedWithTestNotExists(String rawTestNameStr) {
        RawTestName rawTestName = rawTestNameService.findByRawName(rawTestNameStr);
        return rawTestName == null || rawTestName.getTest() == null;
    }

    private PID getPID(ORU_R01 oru_r01) {
        return getHl7Patient(oru_r01).getPID();
    }

    private boolean isPatientRegistered(ORU_R01 oru_r01) throws HL7Exception {
        return getStay(oru_r01) != null;
    }

    private List<Pair<TestRequest, TestResult>>
    filterOutIrrelevantData(List<Pair<TestRequest, TestResult>> testsRequestsAndResults) {
        return testsRequestsAndResults.stream()
                .filter(this::isTestResultWithValue)
                .filter(this::doesNotExistInDB)
                .collect(Collectors.toList());
    }

    private Stay getStay(ORU_R01 oru_r01) throws HL7Exception {
        PV1 pv1 = getPV1(oru_r01);
        String stayHl7Id = pv1Parser.parse(pv1).getHl7Id();
        return stayService.findByHl7Id(stayHl7Id);
    }

    private PV1 getPV1(ORU_R01 oru_r01) {
        return getHl7Patient(oru_r01).getVISIT().getPV1();
    }

    private ORU_R01_PATIENT getHl7Patient(ORU_R01 oru_r01) {
        return oru_r01.getRESPONSE().getPATIENT();
    }

    private List<Pair<TestRequest, TestResult>> parseOBRAndOBX(ORU_R01 oru_r01) throws HL7Exception {
        List<ORU_R01_ORDER_OBSERVATION> observations = oru_r01.getRESPONSE().getORDER_OBSERVATIONAll();
        return observations.stream()
                .map(this::parseOBRAndOBX)
                .collect(Collectors.toList());
    }

    private boolean doesNotExistInDB(Pair<TestRequest, TestResult> testRequestAndResult) {
        TestRequest testRequest = testRequestAndResult.getLeft();
        return testRequestService.findByHl7Id(testRequest.getHl7Id()) == null;
    }

    private boolean isTestResultWithValue(Pair<TestRequest, TestResult> testRequestAndResult) {
        return testRequestAndResult.getRight().getValue() != null;
    }

    private Pair<TestRequest, TestResult>
    mergeTestRequestWithDB(Pair<TestRequest, TestResult> testRequestAndResult) {
        return Pair.of(testRequestService.findByHl7Id(testRequestAndResult.getLeft().getHl7Id()),
                testRequestAndResult.getRight());
    }

    private TestResult associateTestResultWithStay(TestResult testResult, Stay stay) {
        testResult.setStay(stay);
        return testResult;
    }

    private TestResult associateTestResultWithTestRequest(Pair<TestRequest, TestResult> testRequestAndResult) {
        TestRequest testRequest = testRequestAndResult.getLeft();
        TestResult testResult = testRequestAndResult.getRight();
        testResult.setTestRequest(testRequest);

        return testResult;
    }

    private TestRequest associateTestRequestWithTest(TestRequest testRequest) {
        Test test = rawTestNameService.findByRawName(testRequest.getRawTestName()).getTest();
        testRequest.setTest(test);
        return testRequest;
    }

    private Pair<TestRequest, TestResult> parseOBRAndOBX(ORU_R01_ORDER_OBSERVATION orderObservation) {
        try {
            TestRequest testRequest = obrParser.parse(orderObservation.getOBR());
            TestResult testResult = obxParser.parse(orderObservation.getOBSERVATION().getOBX());
            return Pair.of(testRequest, testResult);
        } catch (HL7Exception e) {
            throw new RuntimeException(e);
        }
    }
}
