package com.icupad.nurse.service;

import com.icupad.nurse.model.NurseDiagnosis;

import java.util.Collection;

public interface NurseService {

    NurseDiagnosis save(NurseDiagnosis diagnosis);

    Collection<NurseDiagnosis> findAll();

}
