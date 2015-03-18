package com.icupad.repository;

import com.icupad.domain.test_result.TestResult;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends BaseRepository<TestResult, Long> {
}
