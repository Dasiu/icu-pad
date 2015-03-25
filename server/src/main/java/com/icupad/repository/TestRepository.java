package com.icupad.repository;

import com.icupad.domain.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends BaseRepository<Test, Long> {
    Test findByName(String name);
}
