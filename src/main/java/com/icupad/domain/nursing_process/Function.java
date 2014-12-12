package com.icupad.domain.nursing_process;

import com.icupad.domain.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Function extends NamedEntity {
    @OneToMany(mappedBy = "problem")
    private List<Problem> problems = new ArrayList<>();
    @OneToMany(mappedBy = "activity")
    private List<Activity> activities = new ArrayList<>();

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
