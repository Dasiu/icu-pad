package com.icupad.hl7_gateway.domain;

import com.icupad.hl7_gateway.utils.StringUtils;

import static com.icupad.test_utils.ValidationUtils.*;
import static org.junit.Assert.assertThat;

public class TestModelTest {
    @org.junit.Test
    public void nameShouldBeValid() {
        Test test = new Test();
        test.setName("Morfologia");

        assertThat(validationFor(test, onProperty("name")), succeeds());
    }

    @org.junit.Test
    public void nameShouldNotBeLongerThan15() {
        Test test = new Test();
        test.setName(StringUtils.of(2001));

        assertThat(validationFor(test, onProperty("name")), fails());
    }

    @org.junit.Test
    public void nameShouldNotBeNull() {
        Test test = new Test();

        assertThat(validationFor(test, onProperty("name")), fails());
    }

    @org.junit.Test
    public void nameShouldNotBeEmptyString() {
        Test test = new Test();
        test.setName("");

        assertThat(validationFor(test, onProperty("name")), fails());
    }
}