package com.icupad.test_results.blood_gas.service.impl;

import com.icupad.service.AbstractBaseService;
import com.icupad.test_results.blood_gas.domain.TestRequest;
import com.icupad.test_results.blood_gas.repository.TestRequestRepository;
import com.icupad.test_results.blood_gas.service.TestRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bloodGasTestRequestService")
class TestRequestServiceImpl
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