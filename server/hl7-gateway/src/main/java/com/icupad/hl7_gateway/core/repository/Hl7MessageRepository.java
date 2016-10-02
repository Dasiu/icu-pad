package com.icupad.hl7_gateway.core.repository;

import com.icupad.hl7_gateway.domain.Hl7Message;
import org.springframework.stereotype.Repository;

@Repository
public interface Hl7MessageRepository extends BaseRepository<Hl7Message, Long> {
}
