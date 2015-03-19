package com.icupad.service;

import com.icupad.domain.Patient;

public interface PatientService extends BaseService<Patient> {
    Patient findByHl7Id(String hl7Id);

    boolean existsByHl7Id(String hl7Id);
}
