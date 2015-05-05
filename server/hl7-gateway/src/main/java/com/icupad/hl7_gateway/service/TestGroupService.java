package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.TestGroup;

public interface TestGroupService extends BaseService<TestGroup> {
    TestGroup findByName(String name);

    TestGroup getDefaultGroup();
}
