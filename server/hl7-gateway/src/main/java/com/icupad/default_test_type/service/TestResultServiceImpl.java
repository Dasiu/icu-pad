package com.icupad.default_test_type.service;

import com.icupad.hl7_gateway.service.AbstractBaseService;
import com.icupad.default_test_type.domain.TestResult;
import com.icupad.default_test_type.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("defaultTestResultService")
public class TestResultServiceImpl extends AbstractBaseService<TestResult> implements TestResultService {
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