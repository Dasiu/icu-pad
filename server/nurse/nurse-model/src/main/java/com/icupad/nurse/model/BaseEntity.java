package com.icupad.nurse.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;

	public Long getId() {
		return id;
	}

}
