package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.TestResult;
import com.icupad.hl7_gateway.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestResultServiceImpl  extends AbstractBaseService<TestResult> implements TestResultService {
    private final TestResultRepository testResultRepository;

    @Autowired
    public TestResultServiceImpl(TestResultRepository testResultRepository) {
        super(testResultRepository);

        this.testResultRepository = testResultRepository;
    }

    @Override
    public TestResult findByHl7Id(String hl7Id) {
        return testResultRepository.findByHl7Id(hl7Id);
    }
}
