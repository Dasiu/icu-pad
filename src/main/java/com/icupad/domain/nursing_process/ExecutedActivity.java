package com.icupad.domain.nursing_process;

import com.icupad.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ExecutedActivity extends BaseEntity {
    @NotNull
    @ManyToOne
    private Activity activity;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Person executedBy;

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
}
