package com.icupad.hl7_gateway.service.hl7_server.segment_parser;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v23.datatype.TSComponentOne;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ParserUtils {
    /**
     * @param timeOfAnEvent time of event taken from hl7 message
     * @return local date time with system default time zone or null if timeOfAnEvent is null or contains only quotes
     * @throws DataTypeException
     */
    public static LocalDateTime convertToLocalDateTime(TSComponentOne timeOfAnEvent) throws HL7Exception {
        return isDateExist(timeOfAnEvent)
                ? LocalDateTime.ofInstant(timeOfAnEvent.getValueAsDate().toInstant(), ZoneId.systemDefault())
                : null;
    }

    private static boolean isDateExist(TSComponentOne timeOfAnEvent) throws HL7Exception {
        return !timeOfAnEvent.isEmpty() && !"\"\"".equals(timeOfAnEvent.toString());
    }
}
