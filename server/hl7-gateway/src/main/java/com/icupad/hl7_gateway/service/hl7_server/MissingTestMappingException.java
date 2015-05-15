package com.icupad.hl7_gateway.service.hl7_server;

import com.icupad.hl7_gateway.domain.TestMapping;

import java.util.List;
import java.util.stream.Collectors;

public class MissingTestMappingException extends RuntimeException {
    private static final String messageExpression = "Some HL7 names does not have mappings to tests. " +
            "To fix this, fulfill 'testName', 'unit' and 'testGroup' attributes of following TestMapping entities: %s";

    private final List<TestMapping> testMappings;

    public MissingTestMappingException(List<TestMapping> testMappings) {
        super(String.format(
                messageExpression,
                testMappings.stream()
                        .map(TestMapping::getRawTestName)
                        .collect(Collectors.joining(", "))));
        this.testMappings = testMappings;
    }

    public List<TestMapping> getTestMappings() {
        return testMappings;
    }
}
