package com.icupad.test_results.complete_blood_count.controller.impl;

import com.icupad.controller.AbstractBaseController;
import com.icupad.test_results.complete_blood_count.controller.TestResultController;
import com.icupad.test_results.complete_blood_count.domain.TestResult;
import com.icupad.test_results.complete_blood_count.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testResults")
class TestResultControllerImpl extends AbstractBaseController<TestResult> implements TestResultController {
    private final TestResultService testResultService;

    @Autowired
    public TestResultControllerImpl(TestResultService testResultService) {
        super(testResultService);

        this.testResultService = testResultService;
    }
}