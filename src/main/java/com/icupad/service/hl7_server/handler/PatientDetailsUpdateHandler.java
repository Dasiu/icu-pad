package com.icupad.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.message.ADT_A08;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PatientDetailsUpdateHandler extends AbstractMessageHandler<ADT_A08> {
    @Override
    public Class<ADT_A08> getMessageType() {
        return ADT_A08.class;
    }

    @Override
    public ACK handle(ADT_A08 adt_a08) throws IOException, HL7Exception {
        return generateACK(adt_a08);
    }
}
