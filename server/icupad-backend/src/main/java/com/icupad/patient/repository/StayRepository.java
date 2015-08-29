package com.icupad.patient.repository;

import com.icupad.patient.domain.Stay;
import com.icupad.repository.BaseRepository;

import java.util.Collection;

public interface StayRepository extends BaseRepository<Stay, Long>, StayRepositoryCustom {
}