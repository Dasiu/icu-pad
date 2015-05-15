package com.icupad.hl7_gateway.test_type_module.default_test_type.repository;

import com.icupad.hl7_gateway.core.repository.BaseRepository;
import com.icupad.hl7_gateway.test_type_module.default_test_type.domain.TestResult;
import org.springframework.stereotype.Repository;

@Repository("defaultTestResultRepository")
public interface TestResultRepository extends BaseRepository<TestResult, Long> {
    TestResult findByHl7Id(String hl7Id);
}