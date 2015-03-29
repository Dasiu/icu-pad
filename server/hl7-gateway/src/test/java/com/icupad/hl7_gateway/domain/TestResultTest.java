package com.icupad.hl7_gateway.domain;

import com.icupad.hl7_gateway.utils.StringUtils;
import org.junit.Test;

import static com.icupad.test_utils.ValidationUtils.*;
import static org.junit.Assert.assertThat;

public class TestResultTest {
    @Test
    public void hl7IdShouldBeValid() {
        TestResult testResult = new TestResult();
        testResult.setHl7Id("12345");

        assertThat(validationFor(testResult, onProperty("hl7Id")), succeeds());
    }

    @Test
    public void hl7IdShouldNotBeLongerThan255() {
        TestResult testResult = new TestResult();
        testResult.setHl7Id(StringUtils.of(256));

        assertThat(validationFor(testResult, onProperty("hl7Id")), fails());
    }

    @Test
    public void hl7IdShouldNotBeNull() {
        TestResult testResult = new TestResult();

        assertThat(validationFor(testResult, onProperty("hl7Id")), fails());
    }

    @Test
    public void testRequestShouldNotBeNull() {
        TestResult testResult = new TestResult();

        assertThat(validationFor(testResult, onProperty("testRequest")), fails());
    }

    @Test
    public void stayShouldNotBeNull() {
        TestResult testResult = new TestResult();

        assertThat(validationFor(testResult, onProperty("stay")), fails());
    }
}