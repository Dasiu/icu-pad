package com.icupad.test_results.blood_gas.repository;

import com.icupad.repository.BaseRepository;
import com.icupad.test_results.blood_gas.domain.Test;
import org.springframework.stereotype.Repository;

@Repository("bloodGasTestRepository")
public interface TestRepository extends BaseRepository<Test, Long> {
    Test findByNameAndUnit(String name, String unit);
}