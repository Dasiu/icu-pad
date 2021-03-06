package com.icupad.hl7_gateway.core.service;

import com.icupad.hl7_gateway.core.domain.TestType;

public interface TestTypeService extends BaseService<TestType> {
    TestType findByName(String name);
}
