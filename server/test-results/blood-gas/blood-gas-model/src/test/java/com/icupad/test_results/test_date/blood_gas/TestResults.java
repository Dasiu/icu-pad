package com.icupad.test_results.test_date.blood_gas;

import com.icupad.test_results.blood_gas.domain.TestResult;

public abstract class TestResults {
    public static TestResult testResult() {
        return new TestResult();
    }
}
