package com.icupad.hl7_gateway.service.hl7_server;

import com.icupad.hl7_gateway.domain.TestType;

public class UnsupportedTestTypeException extends RuntimeException {
    private static final String messageExpression = "Following test type is unsupported: %s. To fix this, add " +
            "handler for that test type.";

    public UnsupportedTestTypeException(Class<? extends TestType> testType) {
        super(String.format(messageExpression, testType.getCanonicalName()));
    }
}
