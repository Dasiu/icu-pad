package com.icupad.domain;

import com.icupad.domain.user.User;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AuditableEntity extends BaseEntity {
    @LastModifiedBy
    @ManyToOne
    private User user;
    @LastModifiedDate
    private LocalDateTime modificationDate;
}
