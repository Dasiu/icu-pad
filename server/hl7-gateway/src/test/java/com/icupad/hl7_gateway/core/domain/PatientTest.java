package com.icupad.hl7_gateway.core.domain;

import com.icupad.hl7_gateway.core.utils.StringUtils;
import org.junit.Test;

import java.time.LocalDateTime;

import static com.icupad.test_utils.ValidationUtils.*;
import static org.junit.Assert.assertThat;

public class PatientTest {
    @Test
    public void hl7IdShouldBeValid() {
        Patient patient = new Patient();
        patient.setHl7Id("123456789");

        assertThat(validationFor(patient, onProperty("hl7Id")), succeeds());
    }

    @Test
    public void hl7IdShouldNotBeLongerThan255() {
        Patient patient = new Patient();
        String id256Long = StringUtils.of(256);
        patient.setHl7Id(id256Long);

        assertThat(validationFor(patient, onProperty("hl7Id")), fails());
    }

    @Test
    public void hl7IdShouldNotBeNull() {
        Patient patient = new Patient();

        assertThat(validationFor(patient, onProperty("hl7Id")), fails());
    }

    @Test
    public void peselShouldHas11Digits() {
        Patient patient = new Patient();
        patient.setPesel("12345678901");

        assertThat(validationFor(patient, onProperty("pesel")), succeeds());
    }

    @Test
    public void peselShouldNotBeShorterThan11() {
        Patient patient = new Patient();
        patient.setPesel("1234567890");

        assertThat(validationFor(patient, onProperty("pesel")), fails());
    }

    @Test
    public void peselShouldNotHasNonDigitCharacters() {
        Patient patient = new Patient();
        patient.setPesel("X1234567890");

        assertThat(validationFor(patient, onProperty("pesel")), fails());
    }

    @Test
    public void nameShouldBeValid() {
        Patient patient = new Patient();
        patient.setName("Jan");

        assertThat(validationFor(patient, onProperty("name")), succeeds());
    }

    @Test
    public void nameShouldNotBeLongerThan15() {
        Patient patient = new Patient();
        patient.setName("abcdefghijklmnop");

        assertThat(validationFor(patient, onProperty("name")), fails());
    }

    @Test
    public void nameShouldNotBeNull() {
        Patient patient = new Patient();

        assertThat(validationFor(patient, onProperty("name")), fails());
    }

    @Test
    public void nameShouldNotBeEmptyString() {
        Patient patient = new Patient();
        patient.setName("");

        assertThat(validationFor(patient, onProperty("name")), fails());
    }

    @Test
    public void surnameShouldBeValid() {
        Patient patient = new Patient();
        patient.setSurname("Kowalski");

        assertThat(validationFor(patient, onProperty("surname")), succeeds());
    }

    @Test
    public void surnameShouldNotBeNull() {
        Patient patient = new Patient();

        assertThat(validationFor(patient, onProperty("surname")), fails());
    }

    @Test
    public void surnameShouldNotBeEmptyString() {
        Patient patient = new Patient();
        patient.setSurname("");

        assertThat(validationFor(patient, onProperty("surname")), fails());
    }

    @Test
    public void surnameShouldNotBeLongerThan30() {
        Patient patient = new Patient();
        patient.setSurname("aaaabbbbaaaabbbbaaaabbbbaaaabbb");

        assertThat(validationFor(patient, onProperty("surname")), fails());
    }

    @Test
    public void birthDateShouldBePastDate() {
        Patient patient = new Patient();
        patient.setBirthDate(LocalDateTime.now().minusDays(1));

        assertThat(validationFor(patient, onProperty("birthDate")), succeeds());
    }

    @Test
    public void birthDateShouldNotBeNull() {
        Patient patient = new Patient();

        assertThat(validationFor(patient, onProperty("birthDate")), fails());
    }

    @Test
    public void birthDateShouldNotBeFutureDate() {
        Patient patient = new Patient();
        patient.setBirthDate(LocalDateTime.now().plusDays(1));

        assertThat(validationFor(patient, onProperty("birthDate")), fails());
    }

    @Test
    public void sexShouldBeValid() {
        Patient patient = new Patient();
        patient.setSex(Sex.UNKNOWN);

        assertThat(validationFor(patient, onProperty("sex")), succeeds());
    }

    @Test
    public void sexShouldNotBeNull() {
        Patient patient = new Patient();

        assertThat(validationFor(patient, onProperty("sex")), fails());
    }
}
