package com.icupad.hl7_gateway.core.domain;

import com.icupad.hl7_gateway.core.utils.StringUtils;
import org.junit.Test;

import static com.icupad.hl7_gateway.test_utils.ValidationUtils.*;
import static org.junit.Assert.assertThat;

public class AssignedPatientLocationTest {
    @Test
    public void nameShouldBeValid() {
        AssignedPatientLocation assignedPatientLocation = new AssignedPatientLocation();
        assignedPatientLocation.setName("Klinika dziecięca");

        assertThat(validationFor(assignedPatientLocation, onProperty("name")), succeeds());
    }

    @Test
    public void nameShouldNotBeLongerThan255() {
        AssignedPatientLocation assignedPatientLocation = new AssignedPatientLocation();
        assignedPatientLocation.setName(StringUtils.of(256));

        assertThat(validationFor(assignedPatientLocation, onProperty("name")), fails());
    }
}