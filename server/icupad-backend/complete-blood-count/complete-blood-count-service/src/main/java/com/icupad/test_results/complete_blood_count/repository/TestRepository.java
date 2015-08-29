package com.icupad.test_results.complete_blood_count.repository;

import com.icupad.repository.BaseRepository;
import com.icupad.test_results.complete_blood_count.domain.Test;
import org.springframework.stereotype.Repository;

@Repository("completeBloodCountTestRepository")
public interface TestRepository extends BaseRepository<Test, Long> {
    Test findByNameAndUnit(String name, String unit);
}