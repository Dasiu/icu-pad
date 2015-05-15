package com.icupad.default_test_type.repository;

import com.icupad.hl7_gateway.repository.BaseRepository;
import com.icupad.default_test_type.domain.Test;
import org.springframework.stereotype.Repository;

@Repository("defaultTestRepository")
public interface TestRepository extends BaseRepository<Test, Long> {
    Test findByNameAndUnit(String testName, String unit);
}
