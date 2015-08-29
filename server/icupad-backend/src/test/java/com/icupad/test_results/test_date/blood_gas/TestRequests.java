package com.icupad.test_results.test_date.blood_gas;

import com.icupad.test_results.blood_gas.domain.TestRequest;

public abstract class TestRequests {
    public static TestRequest testRequest() {
        return new TestRequest();
    }
}
