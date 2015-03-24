package com.icupad.domain;

import javax.persistence.Entity;

@Entity
public class Comment extends BaseEntity {
    private String content;
}
