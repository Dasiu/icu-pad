package com.icupad.service;

import com.icupad.domain.TestRequest;
import com.icupad.repository.TestRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestRequestServiceImpl extends AbstractBaseService<TestRequest> implements TestRequestService {
    private final TestRequestRepository testRequestRepository;

    @Autowired
    public TestRequestServiceImpl(TestRequestRepository testRequestRepository) {
        super(testRequestRepository);

        this.testRequestRepository = testRequestRepository;
    }

    @Override
    public TestRequest findByHl7Id(String hl7Id) {
        return testRequestRepository.findByHl7Id(hl7Id);
    }
}
