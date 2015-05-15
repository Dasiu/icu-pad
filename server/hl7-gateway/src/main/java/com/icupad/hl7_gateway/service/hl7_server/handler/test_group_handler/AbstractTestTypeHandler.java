package com.icupad.hl7_gateway.service.hl7_server.handler.test_group_handler;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractTestTypeHandler<TestRequest, TestResult, TestPanelResult> {
    public void handle(Collection<Pair<com.icupad.hl7_gateway.domain.TestRequest,
            com.icupad.hl7_gateway.domain.TestResult>> testRequestsAndResults) {
        testRequestsAndResults.stream()
                .map(this::toSpecificTestRequestType)
                .map(this::toSpecificTestResultsType)
                .collect(Collectors.groupingBy(this::testPanelResult))
                .entrySet().stream()
                .map(this::toTestPanelResult)
                .map(this::saveTestPanelResultIfNotExist)
                .map(this::associateTestRequestsWithTestPanelResult)
                .map(this::toTestResults)
                .flatMap(Collection::stream)
                .map(this::saveTestRequestIfNotExist)
                .forEach(this::saveTestResultIfNotExist);
    }

    protected abstract List<TestResult> toTestResults(TestPanelResult testPanelResult);

    protected abstract TestPanelResult saveTestPanelResultIfNotExist(TestPanelResult testPanelResult);

    protected abstract TestPanelResult
    toTestPanelResult(Map.Entry<TestPanelResult, List<Pair<TestRequest, TestResult>>> groupedRequestsAndResults);

    protected abstract TestResult saveTestResultIfNotExist(TestResult testResult);

    protected abstract TestResult saveTestRequestIfNotExist(TestResult testResult);

    protected abstract TestPanelResult associateTestRequestsWithTestPanelResult(TestPanelResult testPanelResult);

    protected abstract TestPanelResult testPanelResult(Pair<TestRequest, TestResult> requestsAndResults);

    protected abstract Pair<TestRequest, TestResult>
    toSpecificTestResultsType(Pair<TestRequest, com.icupad.hl7_gateway.domain.TestResult> pair);

    protected abstract Pair<TestRequest, com.icupad.hl7_gateway.domain.TestResult>
    toSpecificTestRequestType(Pair<com.icupad.hl7_gateway.domain.TestRequest,
            com.icupad.hl7_gateway.domain.TestResult> requestAndResult);
}
