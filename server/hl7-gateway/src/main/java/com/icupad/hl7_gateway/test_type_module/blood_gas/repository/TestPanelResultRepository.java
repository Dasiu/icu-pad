package com.icupad.hl7_gateway.test_type_module.blood_gas.repository;

import com.icupad.hl7_gateway.domain.TestResultExecutor;
import com.icupad.hl7_gateway.core.repository.BaseRepository;
import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.BloodSource;
import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.TestPanelResult;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository("bloodGasTestPanelResultRepository")
public interface TestPanelResultRepository extends BaseRepository<TestPanelResult, Long> {
    TestPanelResult findByRequestDateAndExecutorAndBloodSource(LocalDateTime requestDate,
                                                               TestResultExecutor executor,
                                                               BloodSource bloodSource);
}