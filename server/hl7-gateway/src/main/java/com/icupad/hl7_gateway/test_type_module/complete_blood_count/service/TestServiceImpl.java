package com.icupad.hl7_gateway.test_type_module.complete_blood_count.service;

import com.icupad.hl7_gateway.core.service.AbstractBaseService;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.Test;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.repository.TestRepository;
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