package com.icupad.test_results.complete_blood_count.controller;

import com.icupad.test_results.complete_blood_count.dto.TestPanelResultDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Collection;

public interface TestResultController {
    Collection<TestPanelResultDTO> index(@PathVariable long stayId,
                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromRequestDate,
                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toRequestDate);
}
