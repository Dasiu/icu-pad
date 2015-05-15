package com.icupad.default_test_type.service;

import com.icupad.hl7_gateway.service.BaseService;
import com.icupad.default_test_type.domain.Test;

public interface TestService extends BaseService<Test> {
    Test findByNameAndUnit(String testName, String unit);
}
