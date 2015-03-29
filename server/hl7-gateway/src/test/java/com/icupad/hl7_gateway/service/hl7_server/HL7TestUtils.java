package com.icupad.hl7_gateway.service.hl7_server;

import ca.uhn.hl7v2.AcknowledgmentCode;
import ca.uhn.hl7v2.model.v23.message.ACK;

public class HL7TestUtils {
    public static AcknowledgmentCode getAcknowledgmentCode(ACK ack) {
        return AcknowledgmentCode.valueOf(ack.getMSA().getAcknowledgementCode().getValue());
    }
}
