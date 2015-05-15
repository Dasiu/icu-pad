package com.icupad.hl7_gateway.test_type_module.complete_blood_count.repository;

import com.icupad.hl7_gateway.core.repository.BaseRepository;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.Test;
import org.springframework.stereotype.Repository;

@Repository("completeBloodCountTestRepository")
public interface TestRepository extends BaseRepository<Test, Long> {
    Test findByNameAndUnit(String name, String unit);
}