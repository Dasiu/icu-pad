package com.icupad.service;

import com.icupad.domain.TestRequest;

public interface TestRequestService extends BaseService<TestRequest> {
    TestRequest findByHl7Id(String hl7Id);
}
