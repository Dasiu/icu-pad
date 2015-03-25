package com.icupad.domain.test_result.fluid_balance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class TestEnumValue extends FluidBalanceValue {
    @Enumerated(EnumType.STRING)
    @Column(name = "test_enum_val")
    TestEnum val;

    public TestEnum getVal() {
        return val;
    }

    public void setVal(TestEnum val) {
        this.val = val;
    }
}
