package com.icupad.domain.validation.validator;

import com.icupad.domain.validation.constraints.Past;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class PastValidator implements ConstraintValidator<Past, LocalDateTime> {

    @Override
    public void initialize(Past past) {
        // empty
    }

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        return localDateTime == null || localDateTime.isBefore(LocalDateTime.now());
    }
}


