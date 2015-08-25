package com.icupad.test_results.complete_blood_count.repository;

import com.icupad.repository.BaseRepository;
import com.icupad.test_results.complete_blood_count.domain.TestResult;
import org.springframework.stereotype.Repository;

@Repository("completeBloodCountTestResultRepository")
public interface TestResultRepository extends BaseRepository<TestResult, Long> {
    TestResult findByHl7Id(String hl7Id);
}