package com.icupad.patient.domain;

import com.icupad.domain.BaseEntity;
import com.icupad.domain.validation.constraints.Past;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Stay extends BaseEntity {
    @Size(min = 1, max = 50)
    @NotNull
    private String hl7Id;

    @NotNull
    private StayType type;

    @Embedded
    private AssignedPatientLocation assignedPatientLocation;

    @Embedded
    private AdmittingDoctor admittingDoctor;

    @Past
    @NotNull
    private LocalDateTime admitDate;

    private LocalDateTime dischargeDate;

    @Column(nullable = false)
    private boolean active;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stay stay = (Stay) o;

        if (active != stay.active) return false;
        if (admitDate != null ? !admitDate.equals(stay.admitDate) : stay.admitDate != null) return false;
        if (assignedPatientLocation != null ? !assignedPatientLocation.equals(stay.assignedPatientLocation) : stay.assignedPatientLocation != null)
            return false;
        if (dischargeDate != null ? !dischargeDate.equals(stay.dischargeDate) : stay.dischargeDate != null)
            return false;
        if (patient != null ? !patient.equals(stay.patient) : stay.patient != null) return false;
        return type == stay.type;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (assignedPatientLocation != null ? assignedPatientLocation.hashCode() : 0);
        result = 31 * result + (admitDate != null ? admitDate.hashCode() : 0);
        result = 31 * result + (dischargeDate != null ? dischargeDate.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (patient != null ? patient.hashCode() : 0);
        return result;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Stay{" +
                "hl7Id='" + hl7Id + '\'' +
                ", type=" + type +
                ", assignedPatientLocation=" + assignedPatientLocation +
                ", admitDate=" + admitDate +
                ", dischargeDate=" + dischargeDate +
                ", active=" + active +
                ", patient=" + patient +
                '}';
    }

    public AdmittingDoctor getAdmittingDoctor() {
        return admittingDoctor;
    }

    public void setAdmittingDoctor(AdmittingDoctor admittingDoctor) {
        this.admittingDoctor = admittingDoctor;
    }
}
