package com.icupad.test_results.blood_gas.controller;

import com.icupad.test_results.blood_gas.domain.Test;
import com.icupad.test_results.blood_gas.domain.TestPanelResult;
import com.icupad.test_results.blood_gas.domain.TestResult;
import com.icupad.test_results.blood_gas.dto.TestDTO;
import com.icupad.test_results.blood_gas.dto.TestPanelResultDTO;
import com.icupad.test_results.blood_gas.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stay/{stayId}")
public class TestPanelResultController {
    private final TestResultService testResultService;

    @Autowired
    public TestPanelResultController(TestResultService testResultService) {
        this.testResultService = testResultService;
    }

    /**
     * @return test panel results for given stay filter through request dates.
     * <p>
     * If dates are not given, all results for stay are returned
     */
    @RequestMapping(value = "/test-panel-result", method = RequestMethod.GET)
    public Collection<TestPanelResultDTO> list(
            @PathVariable long stayId,
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime fromRequestDate,
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime toRequestDate) {
        Collection<TestResult> testResults =
                testResultService.findBetweenRequestDatesForStay(stayId, fromRequestDate, toRequestDate);
        List<TestPanelResultDTO> testPanelResultDtos =
                testResults.stream()
                        .collect(Collectors.groupingBy(testResult -> testResult.getTestRequest().getTestPanelResult()))
                .entrySet().stream()
                .map(this::toTestPanelResultDTO)
                .collect(Collectors.toList());
        return testPanelResultDtos;
    }

    private TestPanelResultDTO toTestPanelResultDTO(Map.Entry<TestPanelResult, List<TestResult>> testPanelResultToResults) {
        List<TestResult> testResults = testPanelResultToResults.getValue();
        List<TestDTO> testDtos = testResults.stream()
                .map(this::toTestDTO)
                .collect(Collectors.toList());
        TestPanelResultDTO testPanelResultDTO = createTestPanelResultDTO(testPanelResultToResults.getKey());
        testPanelResultDTO.getTests().addAll(testDtos);
        return testPanelResultDTO;
    }

    private TestPanelResultDTO createTestPanelResultDTO(TestPanelResult testPanelResult) {
        TestPanelResultDTO testPanelResultDTO = new TestPanelResultDTO();
        testPanelResultDTO.setId(testPanelResult.getId());
        testPanelResultDTO.setRequestDate(testPanelResult.getRequestDate());
        testPanelResultDTO.setExecutor(testPanelResult.getExecutor());
        testPanelResultDTO.setBloodSource(testPanelResult.getBloodSource());
        return testPanelResultDTO;
    }

    private TestDTO toTestDTO(TestResult testResult) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(testResult.getId());
        testDTO.setNorm(testResult.getNorm());
        testDTO.setValue(testResult.getValue());
        testDTO.setAbnormality(testResult.getAbnormality());

        Test test = testResult.getTestRequest().getTest();
        testDTO.setName(test.getName());
        testDTO.setUnit(test.getUnit());

        return testDTO;
    }
}
