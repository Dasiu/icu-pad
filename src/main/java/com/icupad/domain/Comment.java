package com.icupad.domain;

import javax.persistence.Entity;

@Entity
public class Comment extends AuditableEntity {
    private String content;
}
