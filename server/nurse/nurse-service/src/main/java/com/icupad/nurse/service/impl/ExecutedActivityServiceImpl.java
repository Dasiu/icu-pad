package com.icupad.nurse.service.impl;

import com.icupad.nurse.model.ExecutedActivity;
import com.icupad.nurse.repository.ExecutedActivityRepository;
import com.icupad.nurse.service.ExecutedActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

@Component
class ExecutedActivityServiceImpl implements ExecutedActivityService {

	private final ExecutedActivityRepository executedActivityRepository;
	
	@Autowired
	public ExecutedActivityServiceImpl(ExecutedActivityRepository executedActivityRepository) {
		this.executedActivityRepository = executedActivityRepository;
	}

	@Override
	public ExecutedActivity create(ExecutedActivity executedActivity) {
		return executedActivityRepository.save(executedActivity);
	}

	@Override
	public Collection<ExecutedActivity> findByPatientAndDay(Long patinetId, Date date) {
		// TODO Auto-generated method stub
		return executedActivityRepository.findAll();
	}

}
