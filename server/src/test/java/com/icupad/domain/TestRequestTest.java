package com.icupad.domain;

import com.icupad.utils.StringUtils;
import org.junit.Test;

import java.time.LocalDateTime;

import static com.icupad.test_utils.ValidationUtils.*;
import static org.junit.Assert.assertThat;

public class TestRequestTest {
    @Test
    public void hl7IdShouldBeValid() {
        TestRequest testRequest = new TestRequest();
        testRequest.setHl7Id("12345");

        assertThat(validationFor(testRequest, onProperty("hl7Id")), succeeds());
    }


    @Test
    public void hl7IdShouldNotBeLongerThan255() {
        TestRequest testRequest = new TestRequest();
        testRequest.setHl7Id(StringUtils.of(256));

        assertThat(validationFor(testRequest, onProperty("hl7Id")), fails());
    }

    @Test
    public void hl7IdShouldNotBeNull() {
        TestRequest testRequest = new TestRequest();

        assertThat(validationFor(testRequest, onProperty("hl7Id")), fails());
    }

    @Test
    public void requestDateShouldBePastDate() {
        TestRequest testRequest = new TestRequest();
        testRequest.setRequestDate(LocalDateTime.now().minusDays(1));

        assertThat(validationFor(testRequest, onProperty("requestDate")), succeeds());
    }

    @Test
    public void requestDateShouldNotBeFutureDate() {
        TestRequest testRequest = new TestRequest();
        testRequest.setRequestDate(LocalDateTime.now().plusDays(1));

        assertThat(validationFor(testRequest, onProperty("requestDate")), fails());
    }

    @Test
    public void requestDateShouldNotBeNull() {
        TestRequest testRequest = new TestRequest();

        assertThat(validationFor(testRequest, onProperty("requestDate")), fails());
    }

    @Test
    public void testShouldNotBeNull() {
        TestRequest testRequest = new TestRequest();

        assertThat(validationFor(testRequest, onProperty("test")), fails());
    }
}