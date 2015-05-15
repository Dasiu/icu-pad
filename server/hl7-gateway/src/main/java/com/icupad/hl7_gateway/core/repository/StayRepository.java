package com.icupad.hl7_gateway.core.repository;

import com.icupad.hl7_gateway.core.domain.Stay;
import org.springframework.stereotype.Repository;

@Repository
public interface StayRepository extends BaseRepository<Stay, Long> {
    Stay findByHl7Id(String hl7Id);
}
