package com.icupad.hl7_gateway.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.group.ORU_R01_ORDER_OBSERVATION;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.message.ORU_R01;
import ca.uhn.hl7v2.model.v23.segment.PV1;
import com.icupad.hl7_gateway.domain.Stay;
import com.icupad.hl7_gateway.domain.Test;
import com.icupad.hl7_gateway.domain.TestRequest;
import com.icupad.hl7_gateway.domain.TestResult;
import com.icupad.hl7_gateway.service.StayService;
import com.icupad.hl7_gateway.service.TestRequestService;
import com.icupad.hl7_gateway.service.TestResultService;
import com.icupad.hl7_gateway.service.TestService;
import com.icupad.hl7_gateway.service.hl7_server.StayNotFoundException;
import com.icupad.hl7_gateway.service.hl7_server.segment_parser.OBRParser;
import com.icupad.hl7_gateway.service.hl7_server.segment_parser.OBXParser;
import com.icupad.hl7_gateway.service.hl7_server.segment_parser.PV1Parser;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestResultsHandler extends AbstractMessageHandler<ORU_R01> {
    private final StayService stayService;
    private final PV1Parser pv1Parser;
    private final OBRParser obrParser;
    private final OBXParser obxParser;
    private final TestRequestService testRequestService;
    private final TestService testService;
    private final TestResultService testResultService;

    @Autowired
    public TestResultsHandler(StayService stayService,
                              TestRequestService testRequestService,
                              TestResultService testResultService,
                              TestService testService,
                              PV1Parser pv1Parser,
                              OBRParser obrParser,
                              OBXParser obxParser) {
        this.stayService = stayService;
        this.testRequestService = testRequestService;
        this.testResultService = testResultService;
        this.testService = testService;
        this.pv1Parser = pv1Parser;
        this.obrParser = obrParser;
        this.obxParser = obxParser;
    }

    @Override
    public ACK handle(ORU_R01 oru_r01) throws IOException, HL7Exception {
        Stay existingStay = getStay(oru_r01);

        if (existingStay != null) {
            List<Triple<Test, TestRequest, TestResult>> testsRequestsAndResults = parseOBRAndOBX(oru_r01);
            List<Triple<Test, TestRequest, TestResult>> filteredTestsRequestsAndResults =
                    filterOutIrrelevantData(testsRequestsAndResults);

            saveNewTests(filteredTestsRequestsAndResults);

            filteredTestsRequestsAndResults.stream()
                    .map(this::mergeTestWithDB)
                    .map(this::associateTestRequestWithTest)
                    .forEach(testRequestService::save);

            filteredTestsRequestsAndResults.stream()
                    .map(this::mergeTestRequestWithDB)
                    .map(this::associateTestResultWithTestRequest)
                    .map(testResult -> associateTestResultWithStay(testResult, existingStay))
                    .forEach(testResultService::save);
        } else {
            throw new StayNotFoundException();
        }

        return generateACK(oru_r01);
    }

    private List<Triple<Test, TestRequest, TestResult>>
    filterOutIrrelevantData(List<Triple<Test, TestRequest, TestResult>> testsRequestsAndResults) {
        return testsRequestsAndResults.stream()
                .filter(this::isTestResultWithValue)
                .filter(this::doesNotExistInDB)
                .collect(Collectors.toList());
    }

    @Override
    public Class<ORU_R01> getMessageType() {
        return ORU_R01.class;
    }

    private Stay getStay(ORU_R01 oru_r01) throws HL7Exception {
        PV1 pv1 = oru_r01.getRESPONSE().getPATIENT().getVISIT().getPV1();
        String stayHl7Id = pv1Parser.parse(pv1).getHl7Id();
        return stayService.findByHl7Id(stayHl7Id);
    }

    private List<Triple<Test, TestRequest, TestResult>> parseOBRAndOBX(ORU_R01 oru_r01) throws HL7Exception {
        List<ORU_R01_ORDER_OBSERVATION> observations = oru_r01.getRESPONSE().getORDER_OBSERVATIONAll();
        return observations.stream()
                .map(this::parseOBRAndOBX)
                .collect(Collectors.toList());
    }

    private boolean doesNotExistInDB(Triple<Test, TestRequest, TestResult> testRequestAndResult) {
        TestRequest testRequest = testRequestAndResult.getMiddle();
        return testRequestService.findByHl7Id(testRequest.getHl7Id()) == null;
    }

    private boolean isTestResultWithValue(Triple<Test, TestRequest, TestResult> testRequestAndResult) {
        return testRequestAndResult.getRight().getValue() != null;
    }

    private Triple<Test, TestRequest, TestResult>
    mergeTestWithDB(Triple<Test, TestRequest, TestResult> testRequestAndResult) {
        return Triple.of(testService.findByName(testRequestAndResult.getLeft().getName()),
                testRequestAndResult.getMiddle(),
                testRequestAndResult.getRight());
    }

    private Triple<Test, TestRequest, TestResult>
    mergeTestRequestWithDB(Triple<Test, TestRequest, TestResult> testRequestAndResult) {
        return Triple.of(testRequestAndResult.getLeft(),
                testRequestService.findByHl7Id(testRequestAndResult.getMiddle().getHl7Id()),
                testRequestAndResult.getRight());
    }

    private void saveNewTests(List<Triple<Test, TestRequest, TestResult>> testsRequestsAndResults) {
        testsRequestsAndResults.stream()
                .map(Triple::getLeft)
                .filter(this::isTestNotExist)
                .forEach(testService::save);
    }

    private boolean isTestNotExist(Test test) {
        return testService.findByName(test.getName()) == null;
    }

    private TestResult associateTestResultWithStay(TestResult testResult, Stay stay) {
        testResult.setStay(stay);
        return testResult;
    }

    private TestResult associateTestResultWithTestRequest(Triple<Test, TestRequest, TestResult> testRequestAndResult) {
        TestRequest testRequest = testRequestAndResult.getMiddle();
        TestResult testResult = testRequestAndResult.getRight();
        testResult.setTestRequest(testRequest);

        return testResult;
    }

    private TestRequest associateTestRequestWithTest(Triple<Test, TestRequest, TestResult> testRequestAndResult) {
        Test test = testRequestAndResult.getLeft();
        TestRequest testRequest = testRequestAndResult.getMiddle();
        testRequest.setTest(test);

        return testRequest;
    }

    private Triple<Test, TestRequest, TestResult> parseOBRAndOBX(ORU_R01_ORDER_OBSERVATION orderObservation) {
        try {
            Pair<Test, TestRequest> testAndTestRequest = obrParser.parse(orderObservation.getOBR());
            TestResult testResult = obxParser.parse(orderObservation.getOBSERVATION().getOBX());
            return Triple.of(testAndTestRequest.getLeft(), testAndTestRequest.getRight(), testResult);
        } catch (HL7Exception e) {
            throw new RuntimeException(e);
        }
    }
}
