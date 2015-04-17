package com.icupad.nurse.model;

import com.icupad.nurse.model.external.Patient;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ExecutedActivity extends TestResult {
	
	@NotNull
	@ManyToOne
	private Activity activity;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Person executedBy;
	
	@NotNull
	@ManyToOne
	private Patient patient;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@NotNull
	private Short hour;

	public Person getExecutedBy() {
		return executedBy;
	}

	public void setExecutedBy(Person executedBy) {
		this.executedBy = executedBy;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Short getHour() {
		return hour;
	}

	public void setHour(Short hour) {
		this.hour = hour;
	}
}
