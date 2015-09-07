package com.icupad.nurse.service.impl;

import com.icupad.nurse.model.ExecutedActivityType;
import com.icupad.nurse.repository.ExecutedActivityTypeRepository;
import com.icupad.nurse.service.ExecutedActivityTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ExecutedActivityTypeServiceImpl implements ExecutedActivityTypeService {

	private final ExecutedActivityTypeRepository executedActivityTypeRepository;
	
	@Autowired
	public ExecutedActivityTypeServiceImpl(ExecutedActivityTypeRepository executedActivityTypeRepository) {
		this.executedActivityTypeRepository = executedActivityTypeRepository;
	}

	@Override
	public List<ExecutedActivityType> findAll() {
		return executedActivityTypeRepository.findAll();
	}

}
