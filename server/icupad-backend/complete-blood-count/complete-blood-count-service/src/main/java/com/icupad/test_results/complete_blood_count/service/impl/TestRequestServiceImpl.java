package com.icupad.test_results.complete_blood_count.service.impl;

import com.icupad.service.AbstractBaseService;
import com.icupad.test_results.complete_blood_count.domain.TestRequest;
import com.icupad.test_results.complete_blood_count.repository.TestRequestRepository;
import com.icupad.test_results.complete_blood_count.service.TestRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("completeBloodCountTestRequestService")
public class TestRequestServiceImpl
        extends AbstractBaseService<TestRequest> implements TestRequestService {
    private final TestRequestRepository testRequestRepository;

    @Autowired
    public TestRequestServiceImpl(TestRequestRepository
                                              testRequestRepository) {
        super(testRequestRepository);

        this.testRequestRepository = testRequestRepository;
    }

    @Override
    public TestRequest findByHl7Id(String hl7Id) {
        return testRequestRepository.findByHl7Id(hl7Id);
    }
}