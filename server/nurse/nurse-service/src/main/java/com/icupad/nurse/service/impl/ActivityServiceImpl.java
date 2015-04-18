package com.icupad.nurse.service.impl;

import com.icupad.nurse.model.Activity;
import com.icupad.nurse.repository.ActivityRepository;
import com.icupad.nurse.service.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ActivityServiceImpl implements ActivityService {

	private final ActivityRepository activityRepository;
	
	@Autowired
	public ActivityServiceImpl(ActivityRepository activityRepository) {
		this.activityRepository = activityRepository;
	}

	@Override
	public List<Activity> findAll() {
		return activityRepository.findAll();
	}

}
