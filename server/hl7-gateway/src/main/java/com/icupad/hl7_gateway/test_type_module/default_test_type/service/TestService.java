package com.icupad.hl7_gateway.test_type_module.default_test_type.service;

import com.icupad.hl7_gateway.core.service.BaseService;
import com.icupad.hl7_gateway.test_type_module.default_test_type.domain.Test;

public interface TestService extends BaseService<Test> {
    Test findByNameAndUnit(String testName, String unit);
}
