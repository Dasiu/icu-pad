package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.TestMapping;

import java.util.List;

public interface TestMappingService extends BaseService<TestMapping> {
    TestMapping findByRawTestName(String rawTestName);

    TestMapping saveIfNotExistsByRawTestName(TestMapping testMapping);

    List<TestMapping> saveIfNotExistsByRawTestName(Iterable<TestMapping> rawTestNames);
}
