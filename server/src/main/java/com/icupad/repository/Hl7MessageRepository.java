package com.icupad.repository;

import com.icupad.domain.Hl7Message;
import org.springframework.stereotype.Repository;

@Repository
public interface Hl7MessageRepository extends BaseRepository<Hl7Message, Long> {
}
