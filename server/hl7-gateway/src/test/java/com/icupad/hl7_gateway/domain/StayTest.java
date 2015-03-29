package com.icupad.hl7_gateway.domain;

import com.icupad.hl7_gateway.utils.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;

import static com.icupad.test_utils.ValidationUtils.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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

    @Ignore
    public void admittingDoctorShouldBeValid() {
        fail("not implemented yet");
    }

    @Test
    public void admitDateShouldBePastDate() {
        Stay stay = new Stay();
        stay.setAdmitDate(LocalDateTime.now().minusDays(1));

        assertThat(validationFor(stay, onProperty("admitDate")), succeeds());
    }

    @Test
    public void admitDateShouldNotBeNull() {
        Stay stay = new Stay();

        assertThat(validationFor(stay, onProperty("admitDate")), fails());
    }

    @Test
    public void admitDateShouldNotBeFutureDate() {
        Stay stay = new Stay();
        stay.setAdmitDate(LocalDateTime.now().plusDays(1));

        assertThat(validationFor(stay, onProperty("admitDate")), fails());
    }

    @Test
    public void patientShouldNotBeNull() {
        Stay stay = new Stay();

        assertThat(validationFor(stay, onProperty("patient")), fails());
    }
}
