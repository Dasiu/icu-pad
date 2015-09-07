package com.icupad.nurse.model;

import com.icupad.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Problem extends BaseEntity {
	
	@NotNull
	@Column(length = 50)
	protected String name;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "function_id")
	private NurseFunction function;

	@ManyToMany
	private List<Activity> activities = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NurseFunction getFunction() {
		return function;
	}

	public void setFunction(NurseFunction function) {
		this.function = function;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
}
