package com.icupad.nurse.controller;

import com.icupad.common.controller.RestDateFormat;
import com.icupad.nurse.model.ExecutedNurseFunction;
import com.icupad.nurse.model.NurseFunction;
import com.icupad.nurse.service.NurseFunctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/nurse")
public class NurseController {

	private final NurseFunctionService functionService;

	@Autowired
	public NurseController(NurseFunctionService functionService) {
		this.functionService = functionService;
	}

	@RequestMapping("/functions")
	public Collection<NurseFunction> findAllFunctions() {
		return functionService.findAllFunctions();
	}
	
	@RequestMapping(value = "/functions/executed")
	public List<ExecutedNurseFunction> findExecutedFunctionsByPatinetAndDay(
			@RequestParam("patient_id") Long patinetId, 
			@RequestParam("date") @DateTimeFormat(pattern = RestDateFormat.PATTERN) Date date) {
		return functionService.findExecutedFunctionsByPatientAndDay(patinetId, date);
	}

}
