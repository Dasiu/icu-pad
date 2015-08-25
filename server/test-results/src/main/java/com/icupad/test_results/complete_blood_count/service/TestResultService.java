package com.icupad.test_results.complete_blood_count.service;

import com.icupad.service.BaseService;
import com.icupad.test_results.complete_blood_count.domain.TestResult;

public interface TestResultService extends BaseService<TestResult> {
    TestResult findByHl7Id(String hl7Id);
}