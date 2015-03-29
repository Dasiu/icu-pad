package com.icupad.test_data;

import com.icupad.hl7_gateway.domain.*;

import java.time.LocalDateTime;

public class Stays {
    public static Stay createAdamKowalskiStay() {
        Stay stay = new Stay();

        stay.setHl7Id("476711");
        stay.setType(StayType.INPATIENT);

        AssignedPatientLocation assignedPatientLocation = new AssignedPatientLocation();
        assignedPatientLocation.setName("Klinika");

        stay.setAssignedPatientLocation(assignedPatientLocation);
        stay.setAdmitDate(LocalDateTime.of(2012, 4, 22, 18, 47, 0));
        stay.setDischargeDate(LocalDateTime.of(2012, 4, 24, 16, 31, 0));
        stay.setActive(true);
        stay.setPatient(Patients.createAdamKowalski());

        return stay;
    }
}
