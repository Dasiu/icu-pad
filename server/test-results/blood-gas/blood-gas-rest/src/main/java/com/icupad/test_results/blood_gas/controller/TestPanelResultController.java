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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test-panel-result")
public class TestPanelResultController {
    private final TestResultService testResultService;

    @Autowired
    public TestPanelResultController(TestResultService testResultService) {
        this.testResultService = testResultService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<TestPanelResultDTO> list(
            @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endDate) {
        Collection<TestResult> testResults =
                testResultService.findByStartDateAndEndDate(startDate, endDate);
//        Collection<TestDTO> tests = mapToTestDtos(testResults);
        List<TestPanelResultDTO> testPanelResultDtos = testResults.stream().collect(Collectors.groupingBy(testResult -> testResult.getTestRequest().getTestPanelResult()))
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
