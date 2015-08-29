package com.icupad.test_results.complete_blood_count.service;

import com.icupad.service.BaseService;
import com.icupad.test_results.common.domain.TestResultExecutor;
import com.icupad.test_results.complete_blood_count.domain.BloodSource;
import com.icupad.test_results.complete_blood_count.domain.TestPanelResult;

import java.time.LocalDateTime;

public interface TestPanelResultService extends BaseService<TestPanelResult> {
    TestPanelResult findByRequestDateAndExecutorAndBloodSource(LocalDateTime requestDate,
                                                               TestResultExecutor executor,
                                                               BloodSource bloodSource);
}