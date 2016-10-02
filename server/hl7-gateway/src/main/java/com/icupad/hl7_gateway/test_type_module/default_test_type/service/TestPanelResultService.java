package com.icupad.hl7_gateway.test_type_module.default_test_type.service;

import com.icupad.hl7_gateway.domain.TestResultExecutor;
import com.icupad.hl7_gateway.core.service.BaseService;
import com.icupad.hl7_gateway.test_type_module.default_test_type.domain.TestPanelResult;

import java.time.LocalDateTime;

public interface TestPanelResultService extends BaseService<TestPanelResult> {
    TestPanelResult findByRequestDateAndExecutor(LocalDateTime requestDate, TestResultExecutor executor);
}