package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.Test;
import com.icupad.hl7_gateway.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends AbstractBaseService<Test> implements TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestServiceImpl(TestRepository testRepository) {
        super(testRepository);
        this.testRepository = testRepository;
    }

    @Override
    public Test findByName(String name) {
        return testRepository.findByName(name);
    }
}
