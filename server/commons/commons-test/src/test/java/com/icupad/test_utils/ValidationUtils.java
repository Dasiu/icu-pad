package com.icupad.test_utils;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsNot;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtils {
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static Set<ConstraintViolation<Object>> validationFor(Object object, String property) {
        return validator.validateProperty(object, property);
    }

    public static String onProperty(String property) {
        return property;
    }

    public static Matcher<Set<ConstraintViolation<Object>>> succeeds() {
        return new PassesValidation();
    }

    public static Matcher<Set<ConstraintViolation<Object>>> fails() {
        return new IsNot<>(new PassesValidation());
    }

    static class PassesValidation extends BaseMatcher<Set<ConstraintViolation<Object>>> {
        @Override
        public boolean matches(Object o) {
            boolean result = false;
            if (o instanceof Set) {
                result = ((Set) o).isEmpty();
            }
            return result;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("valid");
        }
    }
}