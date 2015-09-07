package com.icupad.nurse.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Activity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String code;
	
	@NotNull
	@Column(length = 50)
	private String name;

	protected Activity() {
	}

	public Activity(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
