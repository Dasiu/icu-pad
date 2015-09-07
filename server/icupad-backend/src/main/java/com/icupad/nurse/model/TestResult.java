package com.icupad.nurse.model;

import com.icupad.domain.BaseEntity;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class TestResult extends BaseEntity {
    @OneToOne
    private Comment comment;

    @OneToOne
    private Stay stay;

    private LocalDateTime executionDate;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Stay getStay() {
        return stay;
    }

    public void setStay(Stay stay) {
        this.stay = stay;
    }

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDateTime executionDate) {
        this.executionDate = executionDate;
    }
}
