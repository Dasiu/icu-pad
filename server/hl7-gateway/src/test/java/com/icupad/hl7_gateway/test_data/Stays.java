package com.icupad.hl7_gateway.test_data;

import com.icupad.hl7_gateway.domain.AdmittingDoctor2;

import java.time.LocalDateTime;

public class Stays {
    public static Stay createAdamKowalskiStay() {
        Stay stay = new Stay();

        stay.setHl7Id("476711");
        stay.setType(StayType.INPATIENT);

        stay.setAssignedPatientLocation(assignedPatientLocation());
        stay.setAdmittingDoctor(admittingDoctor());
        stay.setAdmitDate(LocalDateTime.of(2012, 4, 22, 18, 47, 0));
        stay.setDischargeDate(LocalDateTime.of(2012, 4, 24, 16, 31, 0));
        stay.setActive(true);
        stay.setPatient(Patients.createAdamKowalski());

        return stay;
    }

    private static AdmittingDoctor2 admittingDoctor() {
        AdmittingDoctor2 admittingDoctor2 = new AdmittingDoctor2();

        admittingDoctor2.setHl7Id("245");
        admittingDoctor2.setName("Nowak");
        admittingDoctor2.setSurname("Tomasz");
        admittingDoctor2.setNpwz("5289888");

        return admittingDoctor2;
    }

    private static AssignedPatientLocation assignedPatientLocation() {
        AssignedPatientLocation assignedPatientLocation = new AssignedPatientLocation();
        assignedPatientLocation.setName("Klinika");
        return assignedPatientLocation;
    }
}
