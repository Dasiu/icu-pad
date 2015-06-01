package com.icupad.hl7_gateway.test_type_module.blood_gas.service;

import com.icupad.hl7_gateway.core.service.BaseService;
import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.Test;

public interface TestService extends BaseService<Test> {
    Test findByNameAndUnit(String name, String unit);
}