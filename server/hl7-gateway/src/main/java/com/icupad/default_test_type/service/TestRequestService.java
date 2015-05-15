package com.icupad.default_test_type.service;

import com.icupad.hl7_gateway.service.BaseService;
import com.icupad.default_test_type.domain.TestRequest;

public interface TestRequestService extends BaseService<TestRequest> {
    TestRequest findByHl7Id(String hl7Id);
}