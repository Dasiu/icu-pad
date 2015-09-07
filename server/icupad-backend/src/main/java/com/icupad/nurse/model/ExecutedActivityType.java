package com.icupad.nurse.model;

import javax.persistence.*;

@Entity
@Table(name = "EXECUTED_ACTIVITY_TYPE")
public class ExecutedActivityType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private String code;

	@Column
	private String description;

	protected ExecutedActivityType() {
	}

	public ExecutedActivityType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
