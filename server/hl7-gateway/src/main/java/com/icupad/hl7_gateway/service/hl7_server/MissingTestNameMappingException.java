package com.icupad.hl7_gateway.service.hl7_server;

import com.icupad.hl7_gateway.domain.RawTestName;

import java.util.List;
import java.util.stream.Collectors;

public class MissingTestNameMappingException extends RuntimeException {
    private static final String messageExpression = "Following names does not have mappings to test name. " +
            "To fix this, add Test entities and associate them with new RawTestName entities: %s";

    private final List<RawTestName> rawTestNames;

    public MissingTestNameMappingException(List<RawTestName> rawTestNames) {
        super(String.format(
                messageExpression,
                rawTestNames.stream()
                        .map(RawTestName::getRawName)
                        .collect(Collectors.joining(", "))));
        this.rawTestNames = rawTestNames;
    }

    public List<RawTestName> getRawTestNames() {
        return rawTestNames;
    }
}
