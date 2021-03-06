package com.icupad.hl7_gateway.core.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Represents test type, like for instance blood gas.
 * <p>
 * Every test type module should bring its own test type by extending this class and saving its singleton instance.
 * Instance is needed to associate test mappings with test type.
 *
 * @see com.icupad.hl7_gateway.core.domain.TestMapping
 */
@Entity
@DiscriminatorColumn(
        name = "type",
        discriminatorType = DiscriminatorType.STRING
)
public abstract class TestType extends BaseEntity {
    @Column(nullable = false)
    @Size(min = 1)
    @NotNull
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestType testType = (TestType) o;
        return Objects.equals(name, testType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
