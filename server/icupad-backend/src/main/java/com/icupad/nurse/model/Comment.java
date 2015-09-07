package com.icupad.nurse.model;


import com.icupad.domain.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Comment extends BaseEntity {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
