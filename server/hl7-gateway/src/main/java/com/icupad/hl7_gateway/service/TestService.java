package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.Test;

public interface TestService extends BaseService<Test> {
    Test findByName(String name);
}
