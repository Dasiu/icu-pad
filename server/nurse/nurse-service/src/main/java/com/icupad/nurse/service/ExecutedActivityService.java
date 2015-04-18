package com.icupad.nurse.service;

import com.icupad.nurse.model.ExecutedActivity;

import java.util.Collection;
import java.util.Date;

public interface ExecutedActivityService {

	ExecutedActivity create(ExecutedActivity executedActivity);

	Collection<ExecutedActivity> findByPatientAndDay(Long patinetId, Date date);

}
