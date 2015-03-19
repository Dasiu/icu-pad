package com.icupad.service;

import com.icupad.domain.test_result.TestResult;
import com.icupad.repository.TestResultRepository;
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
}
