package com.icupad.hl7_gateway.test_type_module.complete_blood_count.service;

import com.icupad.hl7_gateway.core.service.AbstractBaseService;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.TestResult;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("completeBloodCountTestResultService")
public class TestResultServiceImpl
        extends AbstractBaseService<TestResult> implements TestResultService {
    private final TestResultRepository testResultRepository;

    @Autowired
    public TestResultServiceImpl(TestResultRepository
                                             testResultRepository) {
        super(testResultRepository);

        this.testResultRepository = testResultRepository;
    }

    @Override
    public TestResult findByHl7Id(String hl7Id) {
        return testResultRepository.findByHl7Id(hl7Id);
    }
}