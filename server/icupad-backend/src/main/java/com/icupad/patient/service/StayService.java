package com.icupad.patient.service;

import com.icupad.patient.domain.Stay;
import com.icupad.service.BaseService;

import java.util.Collection;

public interface StayService extends BaseService<Stay> {
    Collection<Stay> findAllActive();
}