package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.Stay;
import com.icupad.hl7_gateway.repository.StayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StayServiceImpl extends AbstractBaseService<Stay> implements StayService {
    private final StayRepository stayRepository;

    @Autowired
    public StayServiceImpl(StayRepository stayRepository) {
        super(stayRepository);

        this.stayRepository = stayRepository;
    }

    @Override
    public Stay findByHl7Id(String hl7Id) {
        return stayRepository.findByHl7Id(hl7Id);
    }
}
