package com.icupad.hl7_gateway.core.service.hl7_server;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomConstraintViolationException extends ConstraintViolationException {
    public <T> CustomConstraintViolationException(Set<ConstraintViolation<? extends T>> violations) {
        super(violations.stream().map(Object::toString).collect(Collectors.joining("\n")),
                violations);
    }
}
