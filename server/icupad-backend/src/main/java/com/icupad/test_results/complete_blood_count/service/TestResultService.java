package com.icupad.test_results.complete_blood_count.service;

import com.icupad.service.BaseService;
import com.icupad.test_results.complete_blood_count.domain.TestResult;

import java.time.LocalDateTime;
import java.util.Collection;

public interface TestResultService extends BaseService<TestResult> {
    TestResult findByHl7Id(String hl7Id);

    Collection<TestResult> findBetweenRequestDatesForStay(long stayId,
                                                                 LocalDateTime startDate,
                                                                 LocalDateTime endDate);
}