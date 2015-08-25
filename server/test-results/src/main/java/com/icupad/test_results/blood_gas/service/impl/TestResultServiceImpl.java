package com.icupad.test_results.blood_gas.service.impl;

import com.icupad.service.AbstractBaseService;
import com.icupad.test_results.blood_gas.domain.TestResult;
import com.icupad.test_results.blood_gas.repository.TestResultRepository;
import com.icupad.test_results.blood_gas.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service("bloodGasTestResultService")
class TestResultServiceImpl
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

    @Override
    public Collection<TestResult> findBetweenRequestDatesForStay(long stayId,
                                                                 LocalDateTime startDate,
                                                                 LocalDateTime endDate) {
        return testResultRepository.findBetweenRequestDatesForStay(stayId, startDate, endDate);
    }
}