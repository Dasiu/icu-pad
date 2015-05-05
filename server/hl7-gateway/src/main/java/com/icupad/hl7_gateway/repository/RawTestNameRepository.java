package com.icupad.hl7_gateway.repository;

import com.icupad.hl7_gateway.domain.RawTestName;
import org.springframework.stereotype.Repository;

@Repository
public interface RawTestNameRepository extends BaseRepository<RawTestName, Long> {
    RawTestName findByRawName(String rawName);
}
