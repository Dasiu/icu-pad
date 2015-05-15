package com.icupad.hl7_gateway.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.group.ORU_R01_ORDER_OBSERVATION;
import ca.uhn.hl7v2.model.v23.group.ORU_R01_PATIENT;
import ca.uhn.hl7v2.model.v23.message.ORU_R01;
import ca.uhn.hl7v2.model.v23.segment.PID;
import ca.uhn.hl7v2.model.v23.segment.PV1;
import com.icupad.hl7_gateway.domain.*;
import com.icupad.hl7_gateway.service.StayService;
import com.icupad.hl7_gateway.service.TestMappingService;
import com.icupad.hl7_gateway.service.hl7_server.CustomConstraintViolationException;
import com.icupad.hl7_gateway.service.hl7_server.MissingTestMappingException;
import com.icupad.hl7_gateway.service.hl7_server.RegisterPatient;
import com.icupad.hl7_gateway.service.hl7_server.handler.test_group_handler.TestTypeHandler;
import com.icupad.hl7_gateway.service.hl7_server.segment_parser.OBRParser;
import com.icupad.hl7_gateway.service.hl7_server.segment_parser.OBXParser;
import com.icupad.hl7_gateway.service.hl7_server.segment_parser.PV1Parser;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TestResultsHandler implements MessageHandler<ORU_R01> {
    private final RegisterPatient registerPatient;
    private final StayService stayService;
    private final PV1Parser pv1Parser;
    private final OBRParser obrParser;
    private final OBXParser obxParser;
    private final TestMappingService testMappingService;
    private final Function<Class<? extends TestType>, TestTypeHandler> getTestTypeSpecificHandler;
    private final Validator validator;

    @Autowired
    public TestResultsHandler(RegisterPatient registerPatient,
                              StayService stayService,
                              PV1Parser pv1Parser,
                              OBRParser obrParser,
                              OBXParser obxParser,
                              TestMappingService testMappingService,
                              Function<Class<? extends TestType>, TestTypeHandler> getTestTypeSpecificHandler,
                              Validator validator) {
        this.registerPatient = registerPatient;
        this.stayService = stayService;
        this.pv1Parser = pv1Parser;
        this.obrParser = obrParser;
        this.obxParser = obxParser;
        this.testMappingService = testMappingService;
        this.getTestTypeSpecificHandler = getTestTypeSpecificHandler;
        this.validator = validator;
    }

    /**
     * Analyses ORU^R01 message
     * <p>
     * First step is to check if patient is registered. If not, registration is executed.
     * <p>
     * Then test results are mapped to intermediate models.
     * <p>
     * After that intermediate models are further mapped to desired domain models of specific test type. That work is
     * delegated to specific test type handlers.
     * <p>
     * A situation when only partially results are send may occur. Later, in another message, the same results are send
     * including previously missing results. In that case method prevents test results duplication.
     *
     * @param oru_r01 message with test results
     * @throws HL7Exception
     * @throws MissingTestMappingException To normalize hl7 data structures to desired domain models,
     *                                     the mappings between raw test names and proper tests name are required.
     *                                     The method detects missing mappings and throws exception.
     * @see TestTypeHandler
     * @see TestMapping
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
                filterOutEmptyResults(testsRequestsAndResults);


        handleMissingTestMappings(filteredTestsRequestsAndResults);
        handleTestRequests(filteredTestsRequestsAndResults);
        handleTestResults(getStay(oru_r01), filteredTestsRequestsAndResults);

        validate(filteredTestsRequestsAndResults);
        delegateToTestTypeSpecificHandlers(filteredTestsRequestsAndResults);
    }

    private void validate(List<Pair<TestRequest, TestResult>> testsRequestsAndResults) {
        Set<ConstraintViolation<TestRequest>> reqViolations =
                validateRequests(testsRequestsAndResults.stream().map(Pair::getLeft).collect(Collectors.toList()));
        Set<ConstraintViolation<TestResult>> resViolations =
                validateResults(testsRequestsAndResults.stream().map(Pair::getRight).collect(Collectors.toList()));

        Set<ConstraintViolation<?>> violations = new HashSet<>();
        violations.addAll(reqViolations);
        violations.addAll(resViolations);

        if (!violations.isEmpty()) {
            throw new CustomConstraintViolationException(violations);
        }
    }

    private Set<ConstraintViolation<TestResult>> validateResults(List<TestResult> results) {
        return results.stream()
                .map(result -> validator.validate(result))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private Set<ConstraintViolation<TestRequest>> validateRequests(List<TestRequest> requests) {
        return requests.stream()
                .map(request -> validator.validate(request))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }


    private void delegateToTestTypeSpecificHandlers(List<Pair<TestRequest, TestResult>> testsRequestsAndResults) {
        testsRequestsAndResults.stream()
                .collect(Collectors.groupingBy(pair -> pair.getLeft().getTestMapping().getTestType()))
                .entrySet().stream()
                .forEach(this::handleGroupedResults);
    }

    private void handleGroupedResults(Map.Entry<TestType, List<Pair<TestRequest, TestResult>>> testGroupAndResults) {
        TestTypeHandler specificHandler = getTestTypeSpecificHandler.apply(testGroupAndResults.getKey().getClass());
        specificHandler.handle(testGroupAndResults.getValue());
    }

    private void handleTestResults(Stay stay, List<Pair<TestRequest, TestResult>> testsRequestsAndResults)
            throws HL7Exception {
        testsRequestsAndResults.stream()
                .map(this::associateTestResultWithTestRequest)
                .forEach(testResult -> associateTestResultWithStay(testResult, stay));
    }

    private void handleTestRequests(List<Pair<TestRequest, TestResult>> testsRequestsAndResults) {
        testsRequestsAndResults.stream()
                .map(Pair::getLeft)
                .forEach(this::associateTestRequestWithTestMapping);
    }

    private void handleMissingTestMappings(List<Pair<TestRequest, TestResult>> testsRequestsAndResults) {
        List<TestMapping> unknownTestMappings = testsRequestsAndResults.stream()
                .map(this::createTestMapping)
                .filter(this::ifTestMappingIsUnknown)
                .collect(Collectors.toList());

        if (!unknownTestMappings.isEmpty()) {
            throw new MissingTestMappingException(unknownTestMappings);
        }
    }

    private TestMapping createTestMapping(Pair<TestRequest, TestResult> requestAndResult) {
        TestMapping testMapping = new TestMapping();
        testMapping.setRawTestName(requestAndResult.getLeft().getRawTestName());
        testMapping.setUnit(requestAndResult.getRight().getUnit());

        return testMapping;
    }

    private boolean ifTestMappingIsUnknown(TestMapping testMapping) {
        TestMapping fetchedTestMapping = testMappingService.findByRawTestName(testMapping.getRawTestName());
        return fetchedTestMapping == null || fetchedTestMapping.getTestName() == null ||
                fetchedTestMapping.getUnit() == null;
    }

    private PID getPID(ORU_R01 oru_r01) {
        return getHl7Patient(oru_r01).getPID();
    }

    private boolean isPatientRegistered(ORU_R01 oru_r01) throws HL7Exception {
        return getStay(oru_r01) != null;
    }

    private List<Pair<TestRequest, TestResult>>
    filterOutEmptyResults(List<Pair<TestRequest, TestResult>> testsRequestsAndResults) {
        return testsRequestsAndResults.stream()
                .filter(this::isTestResultWithValue)
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

    private boolean isTestResultWithValue(Pair<TestRequest, TestResult> testRequestAndResult) {
        return testRequestAndResult.getRight().getValue() != null;
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

    private TestRequest associateTestRequestWithTestMapping(TestRequest testRequest) {
        TestMapping testMapping = testMappingService.findByRawTestName(testRequest.getRawTestName());
        testRequest.setTestMapping(testMapping);
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
