package com.icupad.service;

import com.icupad.domain.Test;

public interface TestService extends BaseService<Test> {
    Test findByName(String name);
}
