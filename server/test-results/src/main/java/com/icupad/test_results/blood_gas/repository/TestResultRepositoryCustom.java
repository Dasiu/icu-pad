package com.icupad.test_results.blood_gas.repository;

import com.icupad.test_results.blood_gas.domain.TestResult;

import java.time.LocalDateTime;
import java.util.Collection;

public interface TestResultRepositoryCustom {
    Collection<TestResult> findBetweenRequestDatesForStay(long stayId,
                                                          LocalDateTime startDate,
                                                          LocalDateTime endDate);
}
