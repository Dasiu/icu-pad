package com.icupad.domain.test_result;

import com.icupad.domain.BaseEntity;
import com.icupad.domain.Comment;
import com.icupad.domain.Stay;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

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
