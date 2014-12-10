package com.icupad.domain.test_result.fluid_balance;

import com.icupad.domain.WithArtificialId;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
// FIXME come up with better name
public abstract class FluidBalancePosition extends WithArtificialId {
    private String name;
    @Column(name = "group_name")
    private FluidBalanceGroup group;
}
