package com.icupad.hl7_gateway.core.service;

import com.icupad.hl7_gateway.core.domain.TestMapping;

import java.util.List;

public interface TestMappingService extends BaseService<TestMapping> {
    TestMapping findByRawTestName(String rawTestName);

    TestMapping saveIfNotExistsByRawTestName(TestMapping testMapping);

    List<TestMapping> saveIfNotExistsByRawTestName(Iterable<TestMapping> rawTestNames);
}
