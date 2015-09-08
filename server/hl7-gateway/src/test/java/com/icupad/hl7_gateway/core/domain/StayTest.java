package com.icupad.hl7_gateway.core.domain;

import com.icupad.hl7_gateway.core.utils.StringUtils;
import org.junit.Test;

import static com.icupad.hl7_gateway.test_utils.ValidationUtils.*;
import static org.junit.Assert.assertThat;

public class StayTest {
    @Test
    public void hl7IdShouldBeValid() {
        Stay stay = new Stay();
        stay.setHl7Id("476711");

        assertThat(validationFor(stay, onProperty("hl7Id")), succeeds());
    }

    @Test
    public void hl7IdShouldNotBeLongerThan50() {
        Stay stay = new Stay();
        stay.setHl7Id(StringUtils.of(51));

        assertThat(validationFor(stay, onProperty("hl7Id")), fails());
    }

    @Test
    public void hl7IdShouldNotBeNull() {
        Stay stay = new Stay();

        assertThat(validationFor(stay, onProperty("hl7Id")), fails());
    }

    @Test
    public void typeShouldBeValid() {
        Stay stay = new Stay();
        stay.setType(StayType.INPATIENT);

        assertThat(validationFor(stay, onProperty("type")), succeeds());
    }

    @Test
    public void typeShouldNotBeNull() {
        Stay stay = new Stay();

        assertThat(validationFor(stay, onProperty("type")), fails());
    }

    @Test
    public void admitDateShouldNotBeNull() {
        Stay stay = new Stay();

        assertThat(validationFor(stay, onProperty("admitDate")), fails());
    }

    @Test
    public void patientShouldNotBeNull() {
        Stay stay = new Stay();

        assertThat(validationFor(stay, onProperty("patient")), fails());
    }
}
