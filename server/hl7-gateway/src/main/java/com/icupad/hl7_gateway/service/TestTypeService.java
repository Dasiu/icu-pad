package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.TestType;

public interface TestTypeService extends BaseService<TestType> {
    TestType findByName(String name);
}
