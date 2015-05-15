package com.icupad.hl7_gateway.core.service.hl7_server;

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
        logger.error(outgoingMessage);

        if (wasEncodingExceptionThrown(outgoingMessage)) {
            throw new HL7Exception("Processing Error", e);
        } else {
            String responseWithCorrectedAcknowledgmentCode = correctAcknowledgementCode(outgoingMessage, AcknowledgmentCode.CE);
            return deleteTriggerEvent(responseWithCorrectedAcknowledgmentCode);
        }
    }

    private boolean wasEncodingExceptionThrown(String outgoingMessage) {
        // if outgoingMessage is null, it means happi lib can not process message
        // and EncodingNotSupportedException thrown
        return outgoingMessage == null;
    }

    private String deleteTriggerEvent(String ackStr) {
        String findMessageTypeSegment = "\\|ACK.+?\\|";
        String messageTypeSegmentWithoutTriggerEvent = "|ACK|";
        return ackStr.replaceFirst(findMessageTypeSegment, messageTypeSegmentWithoutTriggerEvent);
    }

    private String correctAcknowledgementCode(String outgoingMessage, AcknowledgmentCode acknowledgmentCode) {
        AcknowledgmentCode defaultErrorAcknowledgmentCode = AcknowledgmentCode.AE;

        return outgoingMessage.replace(defaultErrorAcknowledgmentCode.toString(), acknowledgmentCode.toString());
    }
}
