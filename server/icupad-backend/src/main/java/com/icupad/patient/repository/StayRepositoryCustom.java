package com.icupad.patient.repository;

import com.icupad.patient.domain.Stay;

import java.util.Collection;

public interface StayRepositoryCustom {
    Collection<Stay> findAllActive();
}
