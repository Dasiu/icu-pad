package com.icupad.nurse.model;

import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ExecutedNurseFunction extends BaseEntity {
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "function_id")
	private NurseFunction function;
	
	@Column(length = 2000)
	private String diagnosis;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "executed_function_id", referencedColumnName = "id")
	private Collection<ExecutedActivity> executedActivities;

	public NurseFunction getFunction() {
		return function;
	}

	public void setFunction(NurseFunction function) {
		this.function = function;
	}

	public Collection<ExecutedActivity> getExecutedActivities() {
		return executedActivities;
	}

	public void setExecutedActivities(Collection<ExecutedActivity> executedActivities) {
		this.executedActivities = executedActivities;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

}
