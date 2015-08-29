package com.icupad.test_results.test_date.blood_gas;

import com.icupad.test_results.blood_gas.domain.BloodSource;
import com.icupad.test_results.blood_gas.domain.TestPanelResult;

public abstract class TestPanelResults {
    public static TestPanelResult testPanelResult() {
        TestPanelResult testPanelResult = new TestPanelResult();
        testPanelResult.setBloodSource(BloodSource.ARTERY);
        return testPanelResult;
    }
}
