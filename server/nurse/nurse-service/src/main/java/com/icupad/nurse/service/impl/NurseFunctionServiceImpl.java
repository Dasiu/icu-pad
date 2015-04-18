package com.icupad.nurse.service.impl;

import com.icupad.nurse.model.NurseFunction;
import com.icupad.nurse.repository.NurseFunctionRepository;
import com.icupad.nurse.service.NurseFunctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
class NurseFunctionServiceImpl implements NurseFunctionService {

	private final NurseFunctionRepository functionRepository;
	
	@Autowired
	public NurseFunctionServiceImpl(NurseFunctionRepository functionRepository) {
		this.functionRepository = functionRepository;
	}

	@Override
	public Collection<NurseFunction> findAllFunctions() {
		return functionRepository.findAll();
	}

}
