package com.icupad.hl7_gateway.core.service;

import com.icupad.hl7_gateway.core.domain.Hl7Message;
import com.icupad.hl7_gateway.core.repository.Hl7MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Hl7MessageServiceImpl extends AbstractBaseService<Hl7Message> implements Hl7MessageService {
    @Autowired
    public Hl7MessageServiceImpl(Hl7MessageRepository hl7MessageRepository) {
        super(hl7MessageRepository);
    }
}
