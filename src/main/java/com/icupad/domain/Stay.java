package com.icupad.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class Stay extends BaseEntity {
    private Long hl7Id;
    private Timestamp admissionDate;
    private String cause;
    @ManyToOne
    private Patient patient;
}
