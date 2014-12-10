package com.icupad.domain;

import com.icupad.domain.user.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    protected Long id;
    @CreatedBy
    @ManyToOne
    protected User createdBy;
    @CreatedDate
    @Column(columnDefinition = "timestamp")
    protected LocalDateTime createdDate;
    @LastModifiedBy
    @ManyToOne
    protected User lastModifiedBy;
    @LastModifiedDate
    @Column(columnDefinition = "timestamp")
    protected LocalDateTime lastModifiedDate;
}
