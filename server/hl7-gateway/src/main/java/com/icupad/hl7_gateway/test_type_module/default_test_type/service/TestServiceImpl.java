package com.icupad.hl7_gateway.test_type_module.default_test_type.service;

import com.icupad.hl7_gateway.core.service.AbstractBaseService;
import com.icupad.hl7_gateway.test_type_module.default_test_type.domain.Test;
import com.icupad.hl7_gateway.test_type_module.default_test_type.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("defaultTestService")
public class TestServiceImpl extends AbstractBaseService<Test> implements TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestServiceImpl(TestRepository testRepository) {
        super(testRepository);
        this.testRepository = testRepository;
    }

    @Override
    public Test findByNameAndUnit(String testName, String unit) {
        return testRepository.findByNameAndUnit(testName, unit);
    }
}
