package com.icupad.hl7_gateway.test_type_module.blood_gas.service;

import com.icupad.hl7_gateway.core.service.BaseService;
import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.TestResult;

public interface TestResultService extends BaseService<TestResult> {
    TestResult findByHl7Id(String hl7Id);
}