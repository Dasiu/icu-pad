package com.icupad.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.ApplicationException;
import ca.uhn.hl7v2.model.Message;
import com.icupad.service.hl7_server.MessageType;
import com.icupad.service.hl7_server.TriggerEvent;
import org.springframework.stereotype.Component;

import static com.icupad.service.hl7_server.MessageType.ADT;
import static com.icupad.service.hl7_server.TriggerEvent.A08;

@Component
public class PatientDetailsUpdateHandler implements MessageHandler {

    @Override
    public MessageType getMessageType() {
        return ADT;
    }

    @Override
    public TriggerEvent getTriggerEvent() {
        return A08;
    }

    @Override
    public Message processMessage(Message in) throws ApplicationException, HL7Exception {
        return null;
    }

    @Override
    public boolean canProcess(Message in) {
        return true;
    }
}
