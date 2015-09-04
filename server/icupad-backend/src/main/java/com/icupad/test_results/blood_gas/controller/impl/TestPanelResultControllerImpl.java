package com.icupad.test_results.blood_gas.controller.impl;

import com.icupad.test_results.blood_gas.controller.TestPanelResultController;
import com.icupad.test_results.blood_gas.domain.Test;
import com.icupad.test_results.blood_gas.domain.TestPanelResult;
import com.icupad.test_results.blood_gas.domain.TestResult;
import com.icupad.test_results.blood_gas.dto.TestDTO;
import com.icupad.test_results.blood_gas.dto.TestPanelResultDTO;
import com.icupad.test_results.blood_gas.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stay/")
class TestPanelResultControllerImpl implements TestPanelResultController {
    private final TestResultService testResultService;

    @Autowired
    public TestPanelResultControllerImpl(TestResultService testResultService) {
        this.testResultService = testResultService;
    }

    /**
     * @return test panel results for given stay filter through request dates.
     * <p>
     * If dates are not given, all results for stay are returned
     */
    @Override
    @PreAuthorize("hasRole('TEST_RESULTS_READ')")
    @RequestMapping(value = "/{stayId}/test-panel-result", method = RequestMethod.GET)
    public Collection<TestPanelResultDTO> index(@PathVariable long stayId,
                                                @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime fromRequestDate,
                                                @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime toRequestDate) {
        return testResultService.findBetweenRequestDatesForStay(stayId, fromRequestDate, toRequestDate).stream()
                .collect(Collectors.groupingBy(this::testPanelResult))
                .entrySet().stream()
                .map(this::toTestPanelResultDTO)
                .sorted(this::ascendingByRequestDate)
                .collect(Collectors.toList());
    }

    private int ascendingByRequestDate(TestPanelResultDTO testPanelResult1, TestPanelResultDTO testPanelResult2) {
        return testPanelResult2.getRequestDate().compareTo(testPanelResult1.getRequestDate());
    }

    private TestPanelResult testPanelResult(TestResult testResult) {
        return testResult.getTestRequest().getTestPanelResult();
    }

    private TestPanelResultDTO toTestPanelResultDTO(Map.Entry<TestPanelResult, List<TestResult>> testPanelResultToResults) {
        List<TestResult> testResults = testPanelResultToResults.getValue();
        TestPanelResultDTO testPanelResultDTO = createTestPanelResultDTO(testPanelResultToResults.getKey());
        testPanelResultDTO.getTests().addAll(testResults.stream()
                .map(this::toTestDTO)
                .collect(Collectors.toList()));
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
