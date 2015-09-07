package com.icupad.nurse.model;

import com.icupad.domain.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Stay extends BaseEntity {
	private Long hl7Id;

	public Long getHl7Id() {
		return hl7Id;
	}

	public void setHl7Id(Long hl7Id) {
		this.hl7Id = hl7Id;
	}
}
