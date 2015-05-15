package com.icupad.hl7_gateway.test_type_module.complete_blood_count.repository;

import com.icupad.hl7_gateway.core.repository.BaseRepository;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.TestResult;
import org.springframework.stereotype.Repository;

@Repository("completeBloodCountTestResultRepository")
public interface TestResultRepository extends BaseRepository<TestResult, Long> {
    TestResult findByHl7Id(String hl7Id);
}