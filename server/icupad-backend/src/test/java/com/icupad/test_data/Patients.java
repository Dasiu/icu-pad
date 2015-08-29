package com.icupad.test_data;

import com.icupad.patient.domain.Patient;
import com.icupad.patient.domain.Sex;

import java.time.LocalDateTime;

public abstract class Patients {
    public static Patient patient() {
        Patient patient = new Patient();
        patient.setHl7Id("342");
        patient.setPesel("91121234822");
        patient.setName("Adam");
        patient.setSurname("Kowalski");
        patient.setBirthDate(LocalDateTime.now());
        patient.setSex(Sex.FEMALE);
        return patient;
    }
}
