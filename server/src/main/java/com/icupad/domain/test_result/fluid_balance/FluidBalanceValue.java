package com.icupad.domain.test_result.fluid_balance;

import com.icupad.domain.test_result.TestResult;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class FluidBalanceValue extends TestResult {
    @ManyToOne
    private FluidBalancePosition position;

    public FluidBalancePosition getPosition() {
        return position;
    }

    public void setPosition(FluidBalancePosition position) {
        this.position = position;
    }
}
