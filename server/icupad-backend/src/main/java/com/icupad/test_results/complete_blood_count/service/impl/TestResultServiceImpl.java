package com.icupad.test_results.complete_blood_count.service.impl;

import com.icupad.service.AbstractBaseService;
import com.icupad.test_results.complete_blood_count.domain.TestResult;
import com.icupad.test_results.complete_blood_count.repository.TestResultRepository;
import com.icupad.test_results.complete_blood_count.service.TestResultService;
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