package com.icupad.test_results.blood_gas.service.impl;

import com.icupad.service.AbstractBaseService;
import com.icupad.test_results.blood_gas.domain.TestPanelResult;
import com.icupad.test_results.blood_gas.repository.TestPanelResultRepository;
import com.icupad.test_results.blood_gas.service.TestPanelResultService;
import com.icupad.test_results.blood_gas.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}