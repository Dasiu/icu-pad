package com.icupad.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.message.ADT_A01;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PatientRegistrationHandler extends AbstractMessageHandler<ADT_A01> {
    @Override
    public Class<ADT_A01> getMessageType() {
        return ADT_A01.class;
    }

    @Override
    public ACK handle(ADT_A01 adt_a01) throws IOException, HL7Exception {
        return generateACK(adt_a01);
    }
}
