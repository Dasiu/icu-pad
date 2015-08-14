package com.icupad.test_results.blood_gas.service;

import com.icupad.service.BaseService;
import com.icupad.test_results.blood_gas.domain.Test;

public interface TestService extends BaseService<Test> {
    Test findByNameAndUnit(String name, String unit);
}