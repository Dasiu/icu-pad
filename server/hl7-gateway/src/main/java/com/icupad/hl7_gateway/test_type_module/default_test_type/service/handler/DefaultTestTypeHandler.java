package com.icupad.hl7_gateway.test_type_module.default_test_type.service.handler;

import com.icupad.hl7_gateway.core.domain.TestMapping;
import com.icupad.hl7_gateway.core.domain.TestType;
import com.icupad.hl7_gateway.core.service.hl7_server.handler.test_type_handler.AbstractTestTypeHandler;
import com.icupad.hl7_gateway.core.service.hl7_server.handler.test_type_handler.TestTypeHandler;
import com.icupad.hl7_gateway.test_type_module.default_test_type.domain.*;
import com.icupad.hl7_gateway.test_type_module.default_test_type.service.TestPanelResultService;
import com.icupad.hl7_gateway.test_type_module.default_test_type.service.TestRequestService;
import com.icupad.hl7_gateway.test_type_module.default_test_type.service.TestResultService;
import com.icupad.hl7_gateway.test_type_module.default_test_type.service.TestService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DefaultTestTypeHandler
        extends AbstractTestTypeHandler<TestRequest, TestResult, TestPanelResult> implements TestTypeHandler {
    private final TestService testService;
    private final TestPanelResultService testPanelResultService;
    private final TestRequestService testRequestService;
    private final TestResultService testResultService;

    @Autowired
    public DefaultTestTypeHandler(TestService testService,
                                  TestPanelResultService testPanelResultService,
                                  TestRequestService testRequestService,
                                  TestResultService testResultService) {
        this.testService = testService;
        this.testPanelResultService = testPanelResultService;
        this.testRequestService = testRequestService;
        this.testResultService = testResultService;
    }

    @Override
    public Class<? extends TestType> getTestType() {
        return Default.class;
    }

    @Override
    protected List<TestResult> toTestResults(TestPanelResult testPanelResult) {
        return testPanelResult.getTestResults();
    }

    @Override
    protected TestPanelResult saveTestPanelResultIfNotExist(TestPanelResult testPanelResult) {
        TestPanelResult existingTestPanelResult = testPanelResultService.findByRequestDateAndExecutor(
                testPanelResult.getRequestDate(),
                testPanelResult.getExecutor());
        if (existingTestPanelResult != null) {
            return update(existingTestPanelResult, testPanelResult);
        } else {
            return testPanelResultService.save(testPanelResult);
        }
    }

    private TestPanelResult update(TestPanelResult existingTestPanelResult, TestPanelResult testPanelResult) {
        existingTestPanelResult.getTestRequests().clear();
        existingTestPanelResult.addTestRequests(testPanelResult.getTestRequests());

        existingTestPanelResult.getTestResults().clear();
        existingTestPanelResult.addTestResults(testPanelResult.getTestResults());

        return existingTestPanelResult;
    }

    @Override
    protected TestPanelResult
    toTestPanelResult(Map.Entry<TestPanelResult, List<Pair<TestRequest, TestResult>>> groupedRequestsAndResults) {
        TestPanelResult testPanelResult = groupedRequestsAndResults.getKey();
        List<TestRequest> requests = groupedRequestsAndResults.getValue().stream()
                .map(Pair::getLeft).collect(Collectors.toList());

        testPanelResult.addTestRequests(requests);

        List<TestResult> results = groupedRequestsAndResults.getValue().stream()
                .map(Pair::getRight).collect(Collectors.toList());

        testPanelResult.getTestResults().addAll(results);

        return testPanelResult;
    }

    @Override
    protected TestResult saveTestResultIfNotExist(TestResult testResult) {
        TestResult existingTestResult = testResultService.findByHl7Id(testResult.getHl7Id());
        return existingTestResult != null ? existingTestResult : testResultService.save(testResult);
    }

    @Override
    protected TestResult saveTestRequestIfNotExist(TestResult testResult) {
        TestRequest testRequest = testResult.getTestRequest();
        TestRequest existingTestRequest = testRequestService.findByHl7Id(testRequest.getHl7Id());

        if (existingTestRequest == null) {
            testRequestService.save(testRequest);
        }

        return testResult;
    }

    @Override
    protected TestPanelResult associateTestRequestsWithTestPanelResult(TestPanelResult testPanelResult) {
        testPanelResult.getTestRequests().stream()
                .forEach(request -> associateTestRequestWithTestPanelResult(request, testPanelResult));
        return testPanelResult;
    }

    @Override
    protected TestPanelResult testPanelResult(Pair<TestRequest, TestResult> requestsAndResults) {
        return createTestPanelResult(requestsAndResults);
    }

    @Override
    protected Pair<TestRequest, TestResult>
    toSpecificTestResultsType(Pair<TestRequest, com.icupad.hl7_gateway.core.domain.TestResult> pair) {
        TestResult testResult = createTestResult(pair.getRight());

        TestRequest testRequest = pair.getLeft();
        testResult.setTestRequest(testRequest);

        return Pair.of(testRequest, testResult);
    }

    @Override
    protected Pair<TestRequest, com.icupad.hl7_gateway.core.domain.TestResult>
    toSpecificTestRequestType(Pair<com.icupad.hl7_gateway.core.domain.TestRequest,
            com.icupad.hl7_gateway.core.domain.TestResult> requestAndResult) {
        com.icupad.hl7_gateway.core.domain.TestRequest testRequest = requestAndResult.getLeft();
        TestRequest completeBloodCountTestRequest = createTestRequest(testRequest);
        completeBloodCountTestRequest.setTest(findTest(testRequest));

        return Pair.of(completeBloodCountTestRequest, requestAndResult.getRight());
    }

    private void associateTestRequestWithTestPanelResult(TestRequest testRequest, TestPanelResult testPanelResult) {
        testRequest.setTestPanelResult(testPanelResult);
    }

    private TestPanelResult createTestPanelResult(Pair<TestRequest, TestResult> requestsAndResults) {
        TestRequest testRequest = requestsAndResults.getLeft();
        TestResult testResult = requestsAndResults.getRight();

        TestPanelResult testPanelResult = new TestPanelResult();
        testPanelResult.setExecutor(testResult.getExecutor());
        testPanelResult.setRequestDate(testRequest.getRequestDate());
        return testPanelResult;
    }

    private TestResult createTestResult(com.icupad.hl7_gateway.core.domain.TestResult generalTestResult) {
        TestResult testResult = new TestResult();
        testResult.setAbnormality(generalTestResult.getAbnormality());
        testResult.setExecutor(generalTestResult.getExecutor());
        testResult.setHl7Id(generalTestResult.getHl7Id());
        testResult.setNorm(generalTestResult.getNorm());
        testResult.setResultDate(generalTestResult.getResultDate());
        testResult.setStay(generalTestResult.getStay());
        testResult.setUnit(generalTestResult.getUnit());
        testResult.setValue(generalTestResult.getValue());

        return testResult;
    }

    private TestRequest createTestRequest(com.icupad.hl7_gateway.core.domain.TestRequest testRequest) {
        TestRequest completeBloodCountTestRequest = new TestRequest();
        completeBloodCountTestRequest.setHl7Id(testRequest.getHl7Id());
        completeBloodCountTestRequest.setRequestDate(testRequest.getRequestDate());
        return completeBloodCountTestRequest;
    }

    private Test findTest(com.icupad.hl7_gateway.core.domain.TestRequest testRequest) {
        TestMapping testMapping = testRequest.getTestMapping();
        Test test = testService.findByNameAndUnit(testMapping.getTestName(), testMapping.getUnit());
        if (test != null) {
            return test;
        } else {
            return testService.save(createTest(testMapping));
        }
    }

    private Test createTest(TestMapping testMapping) {
        Test test = new Test();
        test.setName(testMapping.getTestName());
        test.setUnit(testMapping.getUnit());
        return test;
    }

}
