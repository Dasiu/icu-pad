package com.icupad.nurse.repository;

import com.icupad.nurse.diagnosis.model.NurseDiagnosis;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseDiagnosisRepository extends JpaRepository<NurseDiagnosis, Long> {

}
