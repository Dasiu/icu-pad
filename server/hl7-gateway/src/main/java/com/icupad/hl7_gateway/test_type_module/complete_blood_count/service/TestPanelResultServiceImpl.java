package com.icupad.hl7_gateway.test_type_module.complete_blood_count.service;

import com.icupad.hl7_gateway.core.domain.TestResultExecutor;
import com.icupad.hl7_gateway.core.service.AbstractBaseService;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.BloodSource;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.TestPanelResult;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.repository.TestPanelResultRepository;
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