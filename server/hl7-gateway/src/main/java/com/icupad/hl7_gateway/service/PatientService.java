package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.Patient;

public interface PatientService extends BaseService<Patient> {
    Patient findByHl7Id(String hl7Id);

    boolean existsByHl7Id(String hl7Id);
}
