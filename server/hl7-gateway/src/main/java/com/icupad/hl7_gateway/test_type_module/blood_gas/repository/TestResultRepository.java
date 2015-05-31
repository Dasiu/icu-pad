package com.icupad.hl7_gateway.test_type_module.blood_gas.repository;

import com.icupad.hl7_gateway.core.repository.BaseRepository;
import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.TestResult;
import org.springframework.stereotype.Repository;

@Repository("bloodGasTestResultRepository")
public interface TestResultRepository extends BaseRepository<TestResult, Long> {
    TestResult findByHl7Id(String hl7Id);
}