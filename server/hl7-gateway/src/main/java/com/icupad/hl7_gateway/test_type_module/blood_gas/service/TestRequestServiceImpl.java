package com.icupad.hl7_gateway.test_type_module.blood_gas.service;

import com.icupad.hl7_gateway.core.service.AbstractBaseService;
import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.TestRequest;
import com.icupad.hl7_gateway.test_type_module.blood_gas.repository.TestRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bloodGasTestRequestService")
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