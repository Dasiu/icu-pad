package com.icupad.domain.test_result.fluid_balance;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BooleanValue extends FluidBalanceValue {
    @Column(name = "boolean_val")
    private boolean val;

    public boolean isVal() {
        return val;
    }

    public void setVal(boolean val) {
        this.val = val;
    }
}
