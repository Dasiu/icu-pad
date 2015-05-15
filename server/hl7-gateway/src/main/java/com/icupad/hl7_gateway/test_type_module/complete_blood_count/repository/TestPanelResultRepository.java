package com.icupad.hl7_gateway.test_type_module.complete_blood_count.repository;

import com.icupad.hl7_gateway.core.domain.TestResultExecutor;
import com.icupad.hl7_gateway.core.repository.BaseRepository;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.BloodSource;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.TestPanelResult;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository("completeBloodCountTestPanelResultRepository")
public interface TestPanelResultRepository extends BaseRepository<TestPanelResult, Long> {
    TestPanelResult findByRequestDateAndExecutorAndBloodSource(LocalDateTime requestDate,
                                                               TestResultExecutor executor,
                                                               BloodSource bloodSource);
}