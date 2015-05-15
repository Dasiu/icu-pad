package com.icupad.hl7_gateway.core.service.hl7_server.segment_parser;

public class AbstractParser {
    private static final String emptyFieldValue = "\"\"";

    /**
     * Some hl7 message's fields can be missing. In such case field has set fixed string value. It must be detected and
     * parsed to null. One exception for that is pesel field, which has special null string value, that field is handled
     * separately.
     *
     * @param field hl7 message field value
     * @return field value or null if detected
     */
    protected String parseNullValue(String field) {
        return !isFieldMissing(field) ? field : null;
    }

    protected static String getEmptyFieldValue() {
        return emptyFieldValue;
    }

    private boolean isFieldMissing(String field) {
        return getEmptyFieldValue().equals(field);
    }
}
