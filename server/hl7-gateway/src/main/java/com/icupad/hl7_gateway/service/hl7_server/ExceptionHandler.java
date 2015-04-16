package com.icupad.hl7_gateway.service.hl7_server;

import ca.uhn.hl7v2.AcknowledgmentCode;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.protocol.ReceivingApplicationExceptionHandler;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ExceptionHandler implements ReceivingApplicationExceptionHandler {
    private static final Logger logger = Logger.getLogger(ExceptionHandler.class);

    @Override
    public String processException(String incomingMessage, Map<String, Object> incomingMetadata,
                                   String outgoingMessage, Exception e) throws HL7Exception {
        logger.error(incomingMessage);
        logger.error(incomingMessage);
        logger.error(outgoingMessage);
        logger.error(e);

        String responseWithCorrectedAcknowledgmentCode = correctAcknowledgementCode(outgoingMessage, AcknowledgmentCode.CE);
        String correctedResponse = deleteTriggerEvent(responseWithCorrectedAcknowledgmentCode);

        logger.error(outgoingMessage);
        return correctedResponse;
    }

    private String deleteTriggerEvent(String ackStr) {
        if (ackStr == null) return null;

        String findMessageTypeSegment = "\\|ACK.+?\\|";
        String messageTypeSegmentWithoutTriggerEvent = "|ACK|";
        return ackStr.replaceFirst(findMessageTypeSegment, messageTypeSegmentWithoutTriggerEvent);
    }

    private String correctAcknowledgementCode(String outgoingMessage, AcknowledgmentCode acknowledgmentCode) {
        if (outgoingMessage == null) return null;
        AcknowledgmentCode defaultErrorAcknowledgmentCode = AcknowledgmentCode.AE;

        return outgoingMessage.replace(defaultErrorAcknowledgmentCode.toString(), acknowledgmentCode.toString());
    }
}