package com.icupad.test_results.complete_blood_count.service.impl;

import com.icupad.service.AbstractBaseService;
import com.icupad.test_results.complete_blood_count.domain.Test;
import com.icupad.test_results.complete_blood_count.repository.TestRepository;
import com.icupad.test_results.complete_blood_count.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("completeBloodCountTestService")
public class TestServiceImpl
        extends AbstractBaseService<Test> implements TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestServiceImpl(TestRepository testRepository) {
        super(testRepository);

        this.testRepository = testRepository;
    }

    @Override
    public Test findByNameAndUnit(String name, String unit) {
        return testRepository.findByNameAndUnit(name, unit);
    }
}