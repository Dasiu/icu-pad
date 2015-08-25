package com.icupad.test_results.complete_blood_count.repository;

import com.icupad.repository.BaseRepository;
import com.icupad.test_results.common.domain.TestResultExecutor;
import com.icupad.test_results.complete_blood_count.domain.BloodSource;
import com.icupad.test_results.complete_blood_count.domain.TestPanelResult;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository("completeBloodCountTestPanelResultRepository")
public interface TestPanelResultRepository extends BaseRepository<TestPanelResult, Long> {
    TestPanelResult findByRequestDateAndExecutorAndBloodSource(LocalDateTime requestDate,
                                                               TestResultExecutor executor,
                                                               BloodSource bloodSource);
}