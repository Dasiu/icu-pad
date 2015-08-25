package com.icupad.test_results.blood_gas.controller;

import com.icupad.test_results.blood_gas.dto.TestPanelResultDTO;

import java.time.LocalDateTime;
import java.util.Collection;

public interface TestPanelResultController {
    Collection<TestPanelResultDTO> index(long stayId, LocalDateTime fromRequestDate, LocalDateTime toRequestDate);
}
