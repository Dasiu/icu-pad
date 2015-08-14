package com.icupad.test_results.blood_gas.service;

import com.icupad.service.BaseService;
import com.icupad.test_results.blood_gas.domain.TestResult;

import java.time.LocalDateTime;
import java.util.Collection;

public interface TestResultService extends BaseService<TestResult> {
    TestResult findByHl7Id(String hl7Id);

    Collection<TestResult> findByStartDateAndEndDate(LocalDateTime startDate, LocalDateTime endDate);
}