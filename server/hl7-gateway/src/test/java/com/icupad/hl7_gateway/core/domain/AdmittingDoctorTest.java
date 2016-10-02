package com.icupad.hl7_gateway.core.domain;

import org.junit.Test;

import static com.icupad.hl7_gateway.test_utils.ValidationUtils.*;
import static org.junit.Assert.assertThat;

public class AdmittingDoctorTest {
    @Test
    public void hl7IdShouldBeValid() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();
        admittingDoctor.setHl7Id("476711");

        assertThat(validationFor(admittingDoctor, onProperty("hl7Id")), succeeds());
    }

    @Test
    public void hl7IdShouldNotBeLongerThan6() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();
        admittingDoctor.setHl7Id("1234567");

        assertThat(validationFor(admittingDoctor, onProperty("hl7Id")), fails());
    }

    @Test
    public void hl7IdShouldNotBeNull() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();

        assertThat(validationFor(admittingDoctor, onProperty("hl7Id")), fails());
    }

    @Test
    public void nameShouldBeValid() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();
        admittingDoctor.setName("Jan");

        assertThat(validationFor(admittingDoctor, onProperty("name")), succeeds());
    }

    @Test
    public void nameShouldNotBeLongerThan15() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();
        admittingDoctor.setName("abcdefghijklmnop");

        assertThat(validationFor(admittingDoctor, onProperty("name")), fails());
    }

    @Test
    public void nameShouldNotBeNull() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();

        assertThat(validationFor(admittingDoctor, onProperty("name")), fails());
    }

    @Test
    public void nameShouldNotBeEmptyString() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();
        admittingDoctor.setName("");

        assertThat(validationFor(admittingDoctor, onProperty("name")), fails());
    }

    @Test
    public void surnameShouldBeValid() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();
        admittingDoctor.setLastname("Kowalski");

        assertThat(validationFor(admittingDoctor, onProperty("surname")), succeeds());
    }

    @Test
    public void surnameShouldNotBeNull() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();

        assertThat(validationFor(admittingDoctor, onProperty("surname")), fails());
    }

    @Test
    public void surnameShouldNotBeEmptyString() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();
        admittingDoctor.setLastname("");

        assertThat(validationFor(admittingDoctor, onProperty("surname")), fails());
    }

    @Test
    public void surnameShouldNotBeLongerThan30() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();
        admittingDoctor.setLastname("aaaabbbbaaaabbbbaaaabbbbaaaabbb");

        assertThat(validationFor(admittingDoctor, onProperty("surname")), fails());
    }

    @Test
    public void npwzShouldBeValid() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();
        admittingDoctor.setNpwz("Ax1335Vfwlekfjwj45234");

        assertThat(validationFor(admittingDoctor, onProperty("npwz")), succeeds());
    }

    @Test
    public void npwzShouldNotBeLongerThan30() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();
        admittingDoctor.setNpwz("aaaabbbbaaaabbbbaaaabbbbaaaabbb");

        assertThat(validationFor(admittingDoctor, onProperty("npwz")), fails());
    }

    @Test
    public void npwzShouldNotBeNull() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();

        assertThat(validationFor(admittingDoctor, onProperty("npwz")), fails());
    }

    @Test
    public void npwzShouldNotBeEmptyString() {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();
        admittingDoctor.setNpwz("");

        assertThat(validationFor(admittingDoctor, onProperty("npwz")), fails());
    }

}
