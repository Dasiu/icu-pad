package com.icupad.hl7_gateway.test_type_module.default_test_type.service;

import com.icupad.hl7_gateway.core.service.AbstractBaseService;
import com.icupad.hl7_gateway.test_type_module.default_test_type.domain.TestRequest;
import com.icupad.hl7_gateway.test_type_module.default_test_type.repository.TestRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("defaultTestRequestService")
public class TestRequestServiceImpl extends AbstractBaseService<TestRequest> implements TestRequestService {
    private final TestRequestRepository testRequestRepository;

    @Autowired
    public TestRequestServiceImpl(TestRequestRepository testRequestRepository) {
        super(testRequestRepository);

        this.testRequestRepository = testRequestRepository;
    }
}