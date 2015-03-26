package com.icupad.nurse.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class NurseFunction extends BaseEntity {
	
    @NotNull
    @Column(length = 50)
    protected String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "function_id", referencedColumnName = "id")
    private List<Activity> activities = new ArrayList<>();

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
}
