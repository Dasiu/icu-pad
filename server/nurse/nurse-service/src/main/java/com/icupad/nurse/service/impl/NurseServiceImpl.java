package com.icupad.nurse.service.impl;

import com.icupad.nurse.diagnosis.model.NurseDiagnosis;
import com.icupad.nurse.repository.NurseDiagnosisRepository;
import com.icupad.nurse.service.NurseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class NurseServiceImpl implements NurseService {

    private final NurseDiagnosisRepository nurseDiagnosisRepository;
    
    @Autowired
    public NurseServiceImpl(NurseDiagnosisRepository nurseDiagnosisRepository) {
        this.nurseDiagnosisRepository = nurseDiagnosisRepository;
    }

    @Override
    public NurseDiagnosis save(NurseDiagnosis diagnosis) {
        return nurseDiagnosisRepository.save(diagnosis);
    }

    @Override
    public Collection<NurseDiagnosis> findAll() {
        return nurseDiagnosisRepository.findAll();
    }

}
