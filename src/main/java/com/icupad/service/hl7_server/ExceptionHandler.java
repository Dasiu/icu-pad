package com.icupad.service.hl7_server;

import ca.uhn.hl7v2.AcknowledgmentCode;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.protocol.ReceivingApplicationExceptionHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ExceptionHandler implements ReceivingApplicationExceptionHandler {
    @Override
    public String processException(String incomingMessage, Map<String, Object> incomingMetadata,
                                   String outgoingMessage, Exception e) throws HL7Exception {
        return setAcknowledgmentCode(outgoingMessage, AcknowledgmentCode.CE);
    }

    private String setAcknowledgmentCode(String outgoingMessage, AcknowledgmentCode acknowledgmentCode) {
        AcknowledgmentCode defaultErrorAcknowledgmentCode = AcknowledgmentCode.AE;
        return outgoingMessage.replace(defaultErrorAcknowledgmentCode.toString(), acknowledgmentCode.toString());
    }
}
