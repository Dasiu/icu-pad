package com.icupad.hl7_gateway.repository;

import com.icupad.hl7_gateway.domain.TestMapping;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMappingRepository extends BaseRepository<TestMapping, Long> {
    TestMapping findByRawTestName(String rawTestName);
}
