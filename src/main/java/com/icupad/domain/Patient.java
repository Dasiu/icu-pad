package com.icupad.domain;

import javax.persistence.Entity;

@Entity
public class Patient extends WithArtificialId {
    private Long hl7Id;
}
