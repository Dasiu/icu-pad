package com.icupad.nurse.service;

import com.icupad.nurse.model.ExecutedNurseFunction;
import com.icupad.nurse.model.NurseFunction;

import java.util.*;

public interface NurseFunctionService {

	Collection<NurseFunction> findAllFunctions();

	List<ExecutedNurseFunction> findExecutedFunctionsByPatientAndDay(Long patinetId, Date date);
	
}
