package com.icupad.hl7_gateway.test_type_module.complete_blood_count.service;

import com.icupad.hl7_gateway.core.service.BaseService;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.Test;

public interface TestService extends BaseService<Test> {
    Test findByNameAndUnit(String name, String unit);
}