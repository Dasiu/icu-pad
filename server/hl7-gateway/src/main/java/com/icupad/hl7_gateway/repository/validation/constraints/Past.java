package com.icupad.hl7_gateway.repository.validation.constraints;

import com.icupad.hl7_gateway.repository.validation.validator.PastValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PastValidator.class)
@Documented
public @interface Past {
    String message() default "The Date must be in the past";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


