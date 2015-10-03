package com.icupad.test_results.complete_blood_count.service.impl;

import com.icupad.service.AbstractBaseService;
import com.icupad.test_results.complete_blood_count.domain.TestResult;
import com.icupad.test_results.complete_blood_count.repository.BloodCountTestResultRepository;
import com.icupad.test_results.complete_blood_count.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service("completeBloodCountTestResultService")
public class TestResultServiceImpl
        extends AbstractBaseService<TestResult> implements TestResultService {
    private final BloodCountTestResultRepository testResultRepository;

    @Autowired
    public TestResultServiceImpl(BloodCountTestResultRepository testResultRepository) {
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
    }}