package com.icupad.hl7_gateway.core.repository;

import com.icupad.hl7_gateway.domain.TestType;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTypeRepository extends BaseRepository<TestType, Long> {
    TestType findByName(String name);
}
