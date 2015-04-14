package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.Stay;

public interface StayService extends BaseService<Stay> {
    Stay findByHl7Id(String hl7Id);
}