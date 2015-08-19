package com.icupad.patient.controller;

import com.icupad.patient.domain.Patient;
import com.icupad.patient.domain.Stay;
import com.icupad.patient.dto.PatientDTO;
import com.icupad.patient.dto.StayDTO;
import com.icupad.patient.service.StayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patient")
public class PatientControllerImpl implements PatientController {
    private final StayService stayService;

    @Autowired
    public PatientControllerImpl(StayService stayService) {
        this.stayService = stayService;
    }

    @Override
    public Collection<PatientDTO> index(@RequestParam boolean findOnlyActive) {
        Collection<Stay> activeStays = stayService.findAllActive();
        return activeStays.stream()
                .collect(Collectors.groupingBy(Stay::getPatient))
                .entrySet().stream()
                .map(this::toPatientDTO)
                .collect(Collectors.toList());
    }

    private PatientDTO toPatientDTO(Map.Entry<Patient, List<Stay>> patientAndStays) {
        PatientDTO patientDTO = createPatientDTO(patientAndStays.getKey());
        patientDTO.getStays().addAll(patientAndStays.getValue().stream()
                .map(this::toStayDTO)
                .collect(Collectors.toList()));
        return patientDTO;
    }

    private StayDTO toStayDTO(Stay stay) {
        StayDTO stayDTO = new StayDTO();
        stayDTO.setId(stay.getId());
        stayDTO.setHl7Id(stay.getHl7Id());
        stayDTO.setActive(stay.isActive());
        stayDTO.setAdmitDate(stay.getAdmitDate());
        stayDTO.setAdmittingDoctor(stay.getAdmittingDoctor());
        stayDTO.setAssignedPatientLocation(stay.getAssignedPatientLocation());
        stayDTO.setDischargeDate(stay.getDischargeDate());
        stayDTO.setType(stay.getType());
        stayDTO.setPatientId(stay.getPatient().getId());
        return stayDTO;
    }

    private PatientDTO createPatientDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setBirthDate(patient.getBirthDate());
        patientDTO.setHl7Id(patient.getHl7Id());
        patientDTO.setPesel(patient.getPesel());
        patientDTO.setSex(patient.getSex());
        patientDTO.setSurname(patient.getSurname());
        return patientDTO;
    }
}
