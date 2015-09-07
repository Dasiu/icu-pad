package com.icupad.nurse.repository;

import com.icupad.nurse.model.ExecutedActivity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutedActivityRepository extends JpaRepository<ExecutedActivity, Long> {
	
}
