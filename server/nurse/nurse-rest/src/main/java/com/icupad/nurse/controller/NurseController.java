package com.icupad.nurse.controller;

import com.google.common.collect.Lists;
import com.icupad.common.controller.RestDateFormat;
import com.icupad.nurse.dto.NurseDiagnosisForm;
import com.icupad.nurse.model.*;
import com.icupad.nurse.service.ExecutedActivityService;
import com.icupad.nurse.service.NurseFunctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/nurse")
public class NurseController {

	private final NurseFunctionService functionService;
	private final ExecutedActivityService executedActivityService;

	@Autowired
	public NurseController(NurseFunctionService functionService, ExecutedActivityService executedActivityService) {
		this.functionService = functionService;
		this.executedActivityService = executedActivityService;
	}

	@RequestMapping("/functions")
	public Collection<NurseFunction> findAllFunctions() {
		return functionService.findAllFunctions();
	}
	
	@RequestMapping(value = "/functions/executed")
	public List<ExecutedNurseFunction> findExecutedFunctionsByPatinetAndDay(
			@RequestParam("patient_id") Long patinetId, 
			@RequestParam("date") @DateTimeFormat(pattern = RestDateFormat.PATTERN) Date date) {
		@SuppressWarnings("unused")
		Collection<ExecutedActivity> executedActivities = executedActivityService.findByPatientAndDay(patinetId, date);
		
		return Lists.newArrayList(new ExecutedNurseFunction());
	}

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ResponseEntity<?> form(@RequestBody NurseDiagnosisForm form) {
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/form/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getFormTemplate(@PathVariable("name") String formName) {
        if (!"NurseDiagnosisForm".equals(formName)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(NurseDiagnosisForm.class.getDeclaredFields(), HttpStatus.OK);
    }

}
