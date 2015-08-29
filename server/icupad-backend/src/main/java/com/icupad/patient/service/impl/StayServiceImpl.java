package com.icupad.patient.service.impl;

import com.icupad.patient.domain.Stay;
import com.icupad.patient.repository.StayRepository;
import com.icupad.patient.service.StayService;
import com.icupad.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
class StayServiceImpl extends AbstractBaseService<Stay> implements StayService {
    private final StayRepository stayRepository;

    @Autowired
    public StayServiceImpl(StayRepository stayRepository) {
        super(stayRepository);

        this.stayRepository = stayRepository;
    }

    @Override
    public Collection<Stay> findAllActive() {
        return stayRepository.findAllActive();
    }
}