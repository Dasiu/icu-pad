package com.icupad.domain.nursing_process;

import com.icupad.domain.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Activity extends NamedEntity {
    @NotNull
    @ManyToOne
    private Function function;

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }
}
