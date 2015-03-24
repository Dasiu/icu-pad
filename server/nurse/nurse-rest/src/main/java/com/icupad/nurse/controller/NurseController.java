package com.icupad.nurse.controller;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icupad.nurse.model.Function;

@RestController
@RequestMapping("/nurse")
public class NurseController {
	
	@RequestMapping("/functions")
	public Collection<Function> findAllFunctions() {
		return Arrays.asList(new Function());
	}

}
