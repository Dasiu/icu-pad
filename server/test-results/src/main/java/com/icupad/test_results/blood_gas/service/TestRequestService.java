package com.icupad.test_results.blood_gas.service;

import com.icupad.service.BaseService;
import com.icupad.test_results.blood_gas.domain.TestRequest;

public interface TestRequestService extends BaseService<TestRequest> {
    TestRequest findByHl7Id(String hl7Id);
}