package com.icupad.nurse.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Function extends BaseEntity {
    @NotNull
    @Column(length = 50)
    protected String name;

    @OneToMany(mappedBy = "function")
    private List<Problem> problems = new ArrayList<>();

    @OneToMany(mappedBy = "function")
    private List<Activity> activities = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
