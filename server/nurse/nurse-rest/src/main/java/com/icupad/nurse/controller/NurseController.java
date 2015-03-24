package com.icupad.nurse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/nurse")
public class NurseController {
	
	@RequestMapping("/")
	public String root() {
		return "asdf";
	}

}
