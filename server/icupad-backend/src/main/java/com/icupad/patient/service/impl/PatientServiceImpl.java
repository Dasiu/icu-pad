package com.icupad.patient.service.impl;

import com.icupad.patient.domain.Patient;
import com.icupad.patient.repository.PatientRepository;
import com.icupad.patient.service.PatientService;
import com.icupad.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class PatientServiceImpl
        extends AbstractBaseService<Patient> implements PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        super(patientRepository);

        this.patientRepository = patientRepository;
    }
}