package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.TestRequest;

public interface TestRequestService extends BaseService<TestRequest> {
    TestRequest findByHl7Id(String hl7Id);
}
