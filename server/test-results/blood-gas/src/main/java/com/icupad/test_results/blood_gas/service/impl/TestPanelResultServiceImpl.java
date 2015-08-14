package com.icupad.test_results.blood_gas.service.impl;

import com.icupad.service.AbstractBaseService;
import com.icupad.test_results.blood_gas.domain.TestPanelResult;
import com.icupad.test_results.blood_gas.domain.TestRequest;
import com.icupad.test_results.blood_gas.domain.TestResult;
import com.icupad.test_results.blood_gas.repository.TestPanelResultRepository;
import com.icupad.test_results.blood_gas.service.TestPanelResultService;
import com.icupad.test_results.blood_gas.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Service("bloodGasTestPanelResultService")
class TestPanelResultServiceImpl
        extends AbstractBaseService<TestPanelResult> implements TestPanelResultService {
    private final TestPanelResultRepository testPanelResultRepository;
    private final TestResultService testResultService;

    @Autowired
    public TestPanelResultServiceImpl(TestPanelResultRepository testPanelResultRepository,
                                      TestResultService testResultService) {
        super(testPanelResultRepository);

        this.testPanelResultRepository = testPanelResultRepository;
        this.testResultService = testResultService;
    }

    @Override
    public Collection<TestPanelResult> findByStartDateAndEndDate(LocalDateTime startDate, LocalDateTime endDate) {
        Collection<TestResult> testResults = testResultService.findByStartDateAndEndDate(startDate, endDate);
        return testResults.stream()
                .map(TestResult::getTestRequest)
                .map(TestRequest::getTestPanelResult)
                .distinct()
                .collect(Collectors.toList());
    }
}