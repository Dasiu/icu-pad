package com.icupad.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.ApplicationException;
import ca.uhn.hl7v2.model.Message;
import com.icupad.service.hl7_server.MessageType;
import com.icupad.service.hl7_server.TriggerEvent;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.icupad.service.hl7_server.MessageType.ADT;
import static com.icupad.service.hl7_server.TriggerEvent.A01;

@Component
public class PatientRegistrationHandler implements MessageHandler {
    private static final Logger logger = Logger.getLogger(PatientRegistrationHandler.class);

    @Override
    public MessageType getMessageType() {
        return ADT;
    }

    @Override
    public TriggerEvent getTriggerEvent() {
        return A01;
    }

    @Override
    public Message processMessage(Message in) throws ApplicationException, HL7Exception {
        logger.debug(in);

        try {
            return in.generateACK();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean canProcess(Message in) {
        return true;
    }
}
