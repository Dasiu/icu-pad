package com.icupad.domain;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Role extends BaseEntity {
    @NotNull
    @Column(length = 50)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Right> rights = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRight(Right right) {
        getRights().add(right);
    }

    public Set<Right> getRights() {
        return rights;
    }

    public void addRights(List<Right> rights) {
        this.rights.addAll(rights);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
