package com.icupad.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class WithArtificialId extends BaseEntity {
    @Id
    @GeneratedValue
    protected Long id;
}
