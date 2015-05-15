package com.icupad.hl7_gateway.core.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;

public interface MessageHandler<M extends Message> {
    void handle(M m) throws HL7Exception;

    Class<M> getMessageType();
}
