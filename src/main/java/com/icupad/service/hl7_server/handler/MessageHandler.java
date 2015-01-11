package com.icupad.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v23.message.ACK;

import java.io.IOException;

public interface MessageHandler<M extends Message> {
    Class<M> getMessageType();

    ACK handle(M m) throws IOException, HL7Exception;
}
