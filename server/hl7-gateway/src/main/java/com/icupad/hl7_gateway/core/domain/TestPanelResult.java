package com.icupad.hl7_gateway.core.domain;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class TestPanelResult {
    private String testPanelResultId;
    private List<Pair<TestRequest, TestResult>> testRequestsAndResults;

    public String getTestPanelResultId() {
        return testPanelResultId;
    }

    public void setTestPanelResultId(String testPanelResultId) {
        this.testPanelResultId = testPanelResultId;
    }

    public List<Pair<TestRequest, TestResult>> getTestRequestsAndResults() {
        return testRequestsAndResults;
    }

    public void setTestRequestsAndResults(List<Pair<TestRequest, TestResult>> testRequestsAndResults) {
        this.testRequestsAndResults = testRequestsAndResults;
    }
}
