package com.icupad.test_results.complete_blood_count.repository;

import com.icupad.test_results.complete_blood_count.domain.TestResult;

import java.time.LocalDateTime;
import java.util.Collection;

public interface BloodCountTestResultRepositoryCustom {
    Collection<TestResult> findBetweenRequestDatesForStay(long stayId,
                                                          LocalDateTime startDate,
                                                          LocalDateTime endDate);
}
