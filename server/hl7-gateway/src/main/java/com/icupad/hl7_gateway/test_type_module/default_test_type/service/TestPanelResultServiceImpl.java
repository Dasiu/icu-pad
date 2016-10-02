package com.icupad.hl7_gateway.test_type_module.default_test_type.service;

import com.icupad.hl7_gateway.domain.TestResultExecutor;
import com.icupad.hl7_gateway.core.service.AbstractBaseService;
import com.icupad.hl7_gateway.test_type_module.default_test_type.domain.TestPanelResult;
import com.icupad.hl7_gateway.test_type_module.default_test_type.repository.TestPanelResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("defaultTestPanelResultService")
public class TestPanelResultServiceImpl extends AbstractBaseService<TestPanelResult> implements TestPanelResultService {
    private final TestPanelResultRepository testPanelResultRepository;

    @Autowired
    public TestPanelResultServiceImpl(TestPanelResultRepository testPanelResultRepository) {
        super(testPanelResultRepository);

        this.testPanelResultRepository = testPanelResultRepository;
    }

    @Override
    public TestPanelResult findByRequestDateAndExecutor(LocalDateTime requestDate, TestResultExecutor executor) {
        return testPanelResultRepository.findByRequestDateAndExecutor(requestDate, executor);
    }
}