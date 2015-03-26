package com.icupad.nurse.controller;

import com.icupad.nurse.model.NurseFunction;
import com.icupad.nurse.service.NurseFunctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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
		return functionService.findAll();
	}

}
