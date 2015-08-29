package com.icupad.test_results.blood_gas.service.impl;

import com.icupad.service.AbstractBaseService;
import com.icupad.test_results.blood_gas.domain.Test;
import com.icupad.test_results.blood_gas.repository.TestRepository;
import com.icupad.test_results.blood_gas.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bloodGasTestService")
class TestServiceImpl
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