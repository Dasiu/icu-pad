package com.icupad.repository;

import com.icupad.domain.Stay;
import org.springframework.stereotype.Repository;

@Repository
public interface StayRepository extends BaseRepository<Stay, Long> {
    Stay findByHl7Id(String hl7Id);
}
