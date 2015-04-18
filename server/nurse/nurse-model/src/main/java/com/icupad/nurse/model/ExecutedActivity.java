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
	@ManyToOne
	private ExecutedActivityType type;
	
	@NotNull
	@ManyToOne
	private Patient patient;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@NotNull
	private Short hour;

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

	public ExecutedActivityType getType() {
		return type;
	}

	public void setType(ExecutedActivityType type) {
		this.type = type;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
