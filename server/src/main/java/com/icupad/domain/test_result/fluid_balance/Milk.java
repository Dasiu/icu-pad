package com.icupad.domain.test_result.fluid_balance;

import javax.persistence.Entity;

@Entity
public class Milk extends FluidBalancePosition {
    private Integer calorific;

    public Integer getCalorific() {
        return calorific;
    }

    public void setCalorific(Integer calorific) {
        this.calorific = calorific;
    }
}
