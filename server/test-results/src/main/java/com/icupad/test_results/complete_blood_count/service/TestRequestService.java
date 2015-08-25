package com.icupad.test_results.complete_blood_count.service;

import com.icupad.service.BaseService;
import com.icupad.test_results.complete_blood_count.domain.TestRequest;

public interface TestRequestService extends BaseService<TestRequest> {
    TestRequest findByHl7Id(String hl7Id);
}