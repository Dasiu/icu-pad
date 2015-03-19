package com.icupad.repository;

import com.icupad.domain.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends BaseRepository<Patient, Long> {
    Patient findByHl7Id(String hl7Id);
}
