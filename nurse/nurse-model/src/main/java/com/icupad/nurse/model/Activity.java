package com.icupad.nurse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Activity extends BaseEntity {
	@NotNull
	@Column(length = 50)
	protected String name;

	@NotNull
	@ManyToOne
	private Function function;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}
}
