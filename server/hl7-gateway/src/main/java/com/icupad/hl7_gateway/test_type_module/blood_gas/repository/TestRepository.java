package com.icupad.hl7_gateway.test_type_module.blood_gas.repository;

import com.icupad.hl7_gateway.core.repository.BaseRepository;
import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.Test;
import org.springframework.stereotype.Repository;

@Repository("bloodGasTestRepository")
public interface TestRepository extends BaseRepository<Test, Long> {
    Test findByNameAndUnit(String name, String unit);
}