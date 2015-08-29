package com.icupad.test_results.test_date.blood_gas;

import com.icupad.test_results.blood_gas.domain.Test;

public abstract class Tests {
    public static Test test() {
        return new Test();
    }
}
