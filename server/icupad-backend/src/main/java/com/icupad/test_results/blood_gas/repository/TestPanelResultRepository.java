package com.icupad.test_results.blood_gas.repository;

import com.icupad.repository.BaseRepository;
import com.icupad.test_results.common.domain.TestResultExecutor;
import com.icupad.test_results.blood_gas.domain.BloodSource;
import com.icupad.test_results.blood_gas.domain.TestPanelResult;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository("bloodGasTestPanelResultRepository")
public interface TestPanelResultRepository extends BaseRepository<TestPanelResult, Long> {
    TestPanelResult findByRequestDateAndExecutorAndBloodSource(LocalDateTime requestDate,
                                                               TestResultExecutor executor,
                                                               BloodSource bloodSource);
}