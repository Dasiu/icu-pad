package com.icupad.patient.dto;

import com.icupad.patient.domain.AdmittingDoctor;
import com.icupad.patient.domain.AssignedPatientLocation;
import com.icupad.patient.domain.StayType;

import java.time.LocalDateTime;

public class StayDTO {
    private long id;
    private String hl7Id;
    private StayType type;
    private AssignedPatientLocation assignedPatientLocation;
    private AdmittingDoctor admittingDoctor;
    private LocalDateTime admitDate;
    private LocalDateTime dischargeDate;
    private boolean active;
    private long patientId;

    public String getHl7Id() {
        return hl7Id;
    }

    public void setHl7Id(String hl7Id) {
        this.hl7Id = hl7Id;
    }

    public StayType getType() {
        return type;
    }

    public void setType(StayType type) {
        this.type = type;
    }

    public AssignedPatientLocation getAssignedPatientLocation() {
        return assignedPatientLocation;
    }

    public void setAssignedPatientLocation(AssignedPatientLocation assignedPatientLocation) {
        this.assignedPatientLocation = assignedPatientLocation;
    }

    public AdmittingDoctor getAdmittingDoctor() {
        return admittingDoctor;
    }

    public void setAdmittingDoctor(AdmittingDoctor admittingDoctor) {
        this.admittingDoctor = admittingDoctor;
    }

    public LocalDateTime getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(LocalDateTime admitDate) {
        this.admitDate = admitDate;
    }

    public LocalDateTime getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDateTime dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
