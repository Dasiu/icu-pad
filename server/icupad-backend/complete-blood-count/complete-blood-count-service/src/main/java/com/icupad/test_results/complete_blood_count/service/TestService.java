package com.icupad.test_results.complete_blood_count.service;

import com.icupad.service.BaseService;
import com.icupad.test_results.complete_blood_count.domain.Test;

public interface TestService extends BaseService<Test> {
    Test findByNameAndUnit(String name, String unit);
}