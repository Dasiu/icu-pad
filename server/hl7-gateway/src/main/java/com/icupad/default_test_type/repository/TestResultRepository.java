package com.icupad.default_test_type.repository;

import com.icupad.hl7_gateway.repository.BaseRepository;
import com.icupad.default_test_type.domain.TestResult;
import org.springframework.stereotype.Repository;

@Repository("defaultTestResultRepository")
public interface TestResultRepository extends BaseRepository<TestResult, Long> {
    TestResult findByHl7Id(String hl7Id);
}