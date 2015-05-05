package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.TestResult;

public interface TestResultService extends BaseService<TestResult> {
    TestResult findByHl7Id(String hl7Id);
}
