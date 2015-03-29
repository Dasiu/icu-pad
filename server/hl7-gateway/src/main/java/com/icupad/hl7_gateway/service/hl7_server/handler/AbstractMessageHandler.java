package com.icupad.hl7_gateway.service.hl7_server.handler;

import ca.uhn.hl7v2.AcknowledgmentCode;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v23.message.ACK;

import java.io.IOException;

public abstract class AbstractMessageHandler<M extends Message> implements MessageHandler<M> {
    protected ACK generateACK(Message msg) throws HL7Exception, IOException {
        ACK ack = (ACK) msg.generateACK();

        deleteTriggerEvent(ack);
        correctAcknowledgementCode(ack);

        return ack;
    }

    private void deleteTriggerEvent(ACK ack) throws DataTypeException {
        ack.getMSH().getMsh9_MessageType().getTriggerEvent().setValue(null);
    }

    private void correctAcknowledgementCode(ACK ack) throws DataTypeException {
        ack.getMSA().getAcknowledgementCode().setValue(AcknowledgmentCode.CA.toString());
    }
}
