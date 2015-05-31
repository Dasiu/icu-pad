package com.icupad.test_results.complete_blood_count.service.impl;

import com.icupad.service.AbstractBaseService;
import com.icupad.test_results.common.domain.TestResultExecutor;
import com.icupad.test_results.complete_blood_count.domain.BloodSource;
import com.icupad.test_results.complete_blood_count.domain.TestPanelResult;
import com.icupad.test_results.complete_blood_count.repository.TestPanelResultRepository;
import com.icupad.test_results.complete_blood_count.service.TestPanelResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("completeBloodCountTestPanelResultService")
public class TestPanelResultServiceImpl
        extends AbstractBaseService<TestPanelResult> implements TestPanelResultService {
    private final TestPanelResultRepository testPanelResultRepository;

    @Autowired
    public TestPanelResultServiceImpl(TestPanelResultRepository
                                                  testPanelResultRepository) {
        super(testPanelResultRepository);

        this.testPanelResultRepository = testPanelResultRepository;
    }

    @Override
    public TestPanelResult findByRequestDateAndExecutorAndBloodSource(LocalDateTime requestDate,
                                                                      TestResultExecutor executor,
                                                                      BloodSource bloodSource) {
        return testPanelResultRepository.findByRequestDateAndExecutorAndBloodSource(requestDate, executor, bloodSource);
    }
}