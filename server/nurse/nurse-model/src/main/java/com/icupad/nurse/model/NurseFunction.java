package com.icupad.nurse.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class NurseFunction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String code;
	
    @NotNull
    @Column(length = 50)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "function_code", referencedColumnName = "code")
    private List<Activity> activities = new ArrayList<>();

    protected NurseFunction() {
	}

	public NurseFunction(String code, String name) {
		this.setCode(code);
		this.name = name;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
    	getActivities().add(activity);
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
