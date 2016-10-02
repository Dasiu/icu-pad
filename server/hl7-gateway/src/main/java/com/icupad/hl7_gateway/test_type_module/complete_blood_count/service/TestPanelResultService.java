package com.icupad.hl7_gateway.test_type_module.complete_blood_count.service;

import com.icupad.hl7_gateway.domain.TestResultExecutor;
import com.icupad.hl7_gateway.core.service.BaseService;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.BloodSource;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.TestPanelResult;

import java.time.LocalDateTime;

public interface TestPanelResultService extends BaseService<TestPanelResult> {
    TestPanelResult findByRequestDateAndExecutorAndBloodSource(LocalDateTime requestDate,
                                                               TestResultExecutor executor,
                                                               BloodSource bloodSource);
}