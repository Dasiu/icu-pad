package com.icupad.hl7_gateway.test_type_module.blood_gas.service;

import com.icupad.hl7_gateway.core.service.BaseService;
import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.TestRequest;

public interface TestRequestService extends BaseService<TestRequest> {
    TestRequest findByHl7Id(String hl7Id);
}