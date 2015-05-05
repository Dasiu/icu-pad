package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.RawTestName;

import java.util.List;

public interface RawTestNameService extends BaseService<RawTestName> {
    RawTestName findByRawName(String rawName);

    RawTestName saveIfNotExists(RawTestName rawTestName);

    List<RawTestName> saveIfNotExists(Iterable<RawTestName> rawTestNames);
}
