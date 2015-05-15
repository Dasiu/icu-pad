package com.icupad.hl7_gateway.test_type_module.complete_blood_count.service;

import com.icupad.hl7_gateway.core.service.AbstractBaseService;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.TestRequest;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.repository.TestRequestRepository;
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