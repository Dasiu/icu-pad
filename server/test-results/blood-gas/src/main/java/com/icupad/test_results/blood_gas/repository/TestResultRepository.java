package com.icupad.test_results.blood_gas.repository;

import com.icupad.repository.BaseRepository;
import com.icupad.test_results.blood_gas.domain.TestResult;
import org.springframework.stereotype.Repository;

@Repository("bloodGasTestResultRepository")
public interface TestResultRepository extends BaseRepository<TestResult, Long>, TestResultRepositoryCustom {
    TestResult findByHl7Id(String hl7Id);
}