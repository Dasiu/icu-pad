package com.icupad.hl7_gateway.repository;

import com.icupad.hl7_gateway.domain.TestGroup;
import org.springframework.stereotype.Repository;

@Repository
public interface TestGroupRepository extends BaseRepository<TestGroup, Long> {
    TestGroup findByName(String name);
}
