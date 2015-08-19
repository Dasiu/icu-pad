package com.icupad.patient.controller;

import com.icupad.patient.dto.PatientDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

public interface PatientController {
    @RequestMapping(method = RequestMethod.GET)
    Collection<PatientDTO> index(@RequestParam boolean findOnlyActive);
}
