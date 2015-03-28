package com.icupad.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Test extends BaseEntity {
    @Column(nullable = false, unique = true, length = 2000)
    @Size(min = 1, max = 2000)
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
