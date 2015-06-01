package com.icupad.hl7_gateway.test_type_module.blood_gas.service;

import com.icupad.hl7_gateway.core.domain.TestResultExecutor;
import com.icupad.hl7_gateway.core.service.BaseService;
import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.BloodSource;
import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.TestPanelResult;

import java.time.LocalDateTime;

public interface TestPanelResultService extends BaseService<TestPanelResult> {
    TestPanelResult findByRequestDateAndExecutorAndBloodSource(LocalDateTime requestDate,
                                                               TestResultExecutor executor,
                                                               BloodSource bloodSource);
}