package com.icupad.hl7_gateway.core.domain;

import com.icupad.hl7_gateway.domain.AdmittingDoctor2;
import org.junit.Test;

import static com.icupad.hl7_gateway.test_utils.ValidationUtils.*;
import static org.junit.Assert.assertThat;

public class AdmittingDoctor2Test {
    @Test
    public void hl7IdShouldBeValid() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();
        admittingDoctor2.setHl7Id("476711");

        assertThat(validationFor(admittingDoctor2, onProperty("hl7Id")), succeeds());
    }

    @Test
    public void hl7IdShouldNotBeLongerThan6() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();
        admittingDoctor2.setHl7Id("1234567");

        assertThat(validationFor(admittingDoctor2, onProperty("hl7Id")), fails());
    }

    @Test
    public void hl7IdShouldNotBeNull() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();

        assertThat(validationFor(admittingDoctor2, onProperty("hl7Id")), fails());
    }

    @Test
    public void nameShouldBeValid() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();
        admittingDoctor2.setName("Jan");

        assertThat(validationFor(admittingDoctor2, onProperty("name")), succeeds());
    }

    @Test
    public void nameShouldNotBeLongerThan15() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();
        admittingDoctor2.setName("abcdefghijklmnop");

        assertThat(validationFor(admittingDoctor2, onProperty("name")), fails());
    }

    @Test
    public void nameShouldNotBeNull() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();

        assertThat(validationFor(admittingDoctor2, onProperty("name")), fails());
    }

    @Test
    public void nameShouldNotBeEmptyString() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();
        admittingDoctor2.setName("");

        assertThat(validationFor(admittingDoctor2, onProperty("name")), fails());
    }

    @Test
    public void surnameShouldBeValid() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();
        admittingDoctor2.setSurname("Kowalski");

        assertThat(validationFor(admittingDoctor2, onProperty("surname")), succeeds());
    }

    @Test
    public void surnameShouldNotBeNull() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();

        assertThat(validationFor(admittingDoctor2, onProperty("surname")), fails());
    }

    @Test
    public void surnameShouldNotBeEmptyString() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();
        admittingDoctor2.setSurname("");

        assertThat(validationFor(admittingDoctor2, onProperty("surname")), fails());
    }

    @Test
    public void surnameShouldNotBeLongerThan30() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();
        admittingDoctor2.setSurname("aaaabbbbaaaabbbbaaaabbbbaaaabbb");

        assertThat(validationFor(admittingDoctor2, onProperty("surname")), fails());
    }

    @Test
    public void npwzShouldBeValid() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();
        admittingDoctor2.setNpwz("Ax1335Vfwlekfjwj45234");

        assertThat(validationFor(admittingDoctor2, onProperty("npwz")), succeeds());
    }

    @Test
    public void npwzShouldNotBeLongerThan30() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();
        admittingDoctor2.setNpwz("aaaabbbbaaaabbbbaaaabbbbaaaabbb");

        assertThat(validationFor(admittingDoctor2, onProperty("npwz")), fails());
    }

    @Test
    public void npwzShouldNotBeNull() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();

        assertThat(validationFor(admittingDoctor2, onProperty("npwz")), fails());
    }

    @Test
    public void npwzShouldNotBeEmptyString() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();
        admittingDoctor2.setNpwz("");

        assertThat(validationFor(admittingDoctor2, onProperty("npwz")), fails());
    }

}
