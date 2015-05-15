package com.icupad.hl7_gateway.core.service.hl7_server.segment_parser;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Segment;

public interface Parser<S extends Segment, T> {
    T parse(S s) throws HL7Exception;
}
