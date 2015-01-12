package com.icupad.domain;

import com.icupad.repository.validation.constraints.Past;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Stay extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Size(min = 1, max = 50)
    @NotNull
    private String hl7Id;

    @Column(nullable = false)
    @NotNull
    private StayType type;

    @Embedded
    private AssignedPatientLocation assignedPatientLocation;

    @Column(nullable = false)
    @Past
    @NotNull
    private LocalDateTime admitDate;

    @Column(nullable = false)
    @Past
    @NotNull
    private LocalDateTime dischargeDate;

    @JoinColumn(nullable = false)
    @ManyToOne
    @NotNull
    private Patient patient;

    public StayType getType() {
        return type;
    }

    public void setType(StayType type) {
        this.type = type;
    }

    public String getHl7Id() {
        return hl7Id;
    }

    public void setHl7Id(String hl7Id) {
        this.hl7Id = hl7Id;
    }

    public LocalDateTime getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(LocalDateTime admitDate) {
        this.admitDate = admitDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public AssignedPatientLocation getAssignedPatientLocation() {
        return assignedPatientLocation;
    }

    public void setAssignedPatientLocation(AssignedPatientLocation assignedPatientLocation) {
        this.assignedPatientLocation = assignedPatientLocation;
    }

    public LocalDateTime getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDateTime dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
}
