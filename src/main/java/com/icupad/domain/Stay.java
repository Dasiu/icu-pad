package com.icupad.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Stay extends WithArtificialId {
    private Long hl7Id;
    private Timestamp admissionDate;
    private String cause;
    @ManyToOne
    private Patient patient;
}
