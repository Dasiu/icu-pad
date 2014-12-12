package com.icupad.domain.nursing_process;

import com.icupad.domain.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Problem extends NamedEntity {
    @NotNull
    @ManyToOne
    private Function function;
    @ManyToMany
    private List<Activity> activities = new ArrayList<>();

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
