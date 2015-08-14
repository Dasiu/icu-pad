package com.icupad.test_results.blood_gas.service;

import com.icupad.service.BaseService;
import com.icupad.test_results.blood_gas.domain.TestPanelResult;

import java.time.LocalDateTime;
import java.util.Collection;

public interface TestPanelResultService extends BaseService<TestPanelResult> {
    Collection<TestPanelResult> findByStartDateAndEndDate(LocalDateTime startDate, LocalDateTime endDate);
}