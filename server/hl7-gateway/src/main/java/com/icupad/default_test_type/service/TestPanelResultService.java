package com.icupad.default_test_type.service;

import com.icupad.hl7_gateway.domain.TestResultExecutor;
import com.icupad.hl7_gateway.service.BaseService;
import com.icupad.default_test_type.domain.TestPanelResult;

import java.time.LocalDateTime;

public interface TestPanelResultService extends BaseService<TestPanelResult> {
    TestPanelResult findByRequestDateAndExecutor(LocalDateTime requestDate, TestResultExecutor executor);
}