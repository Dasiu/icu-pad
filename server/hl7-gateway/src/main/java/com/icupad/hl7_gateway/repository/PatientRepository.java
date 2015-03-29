package com.icupad.hl7_gateway.repository;

import com.icupad.hl7_gateway.domain.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends BaseRepository<Patient, Long> {
    Patient findByHl7Id(String hl7Id);
}
