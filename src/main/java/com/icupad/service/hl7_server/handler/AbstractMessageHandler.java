package com.icupad.service.hl7_server.handler;

import ca.uhn.hl7v2.AcknowledgmentCode;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.segment.MSA;

import java.io.IOException;

public abstract class AbstractMessageHandler<M extends Message> implements MessageHandler<M> {
    protected ACK generateACK(Message msg) throws HL7Exception, IOException {
        ACK ack = (ACK) msg.generateACK();
        MSA ackMSA = ack.getMSA();
        ackMSA.getAcknowledgementCode().setValue(AcknowledgmentCode.CA.toString());
        return ack;
    }
}
