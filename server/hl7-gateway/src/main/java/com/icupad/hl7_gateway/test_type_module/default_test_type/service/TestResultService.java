package com.icupad.hl7_gateway.test_type_module.default_test_type.service;

import com.icupad.hl7_gateway.core.service.BaseService;
import com.icupad.hl7_gateway.test_type_module.default_test_type.domain.TestResult;

public interface TestResultService extends BaseService<TestResult> {
    TestResult findByHl7Id(String hl7Id);
}