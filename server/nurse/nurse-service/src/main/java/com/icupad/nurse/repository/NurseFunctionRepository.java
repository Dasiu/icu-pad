package com.icupad.nurse.repository;

import com.icupad.nurse.model.NurseFunction;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface NurseFunctionRepository extends CrudRepository<NurseFunction, Long> {
	
	Collection<NurseFunction> findAll();

}
