package com.icupad.service;

import com.icupad.domain.Stay;

public interface StayService extends BaseService<Stay> {
    Stay findByHl7Id(String hl7Id);
}
