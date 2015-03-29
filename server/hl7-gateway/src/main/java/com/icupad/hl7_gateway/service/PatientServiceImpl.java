package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.Patient;
import com.icupad.hl7_gateway.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl extends AbstractBaseService<Patient> implements PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        super(patientRepository);

        this.patientRepository = patientRepository;
    }

    @Override
    public Patient findByHl7Id(String hl7Id) {
        return patientRepository.findByHl7Id(hl7Id);
    }

    @Override
    public boolean existsByHl7Id(String hl7Id) {
        return findByHl7Id(hl7Id) != null;
    }
}
