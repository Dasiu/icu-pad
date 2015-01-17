package com.icupad.service.hl7_server.segment_parsers;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Segment;
import com.icupad.domain.BaseEntity;

public interface Parser<S extends Segment, E extends BaseEntity> {
    E parse(S s) throws DataTypeException;
}
