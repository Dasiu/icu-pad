package com.icupad.hl7_gateway.repository;

import com.icupad.hl7_gateway.domain.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends BaseRepository<Test, Long> {
    Test findByName(String name);
}
