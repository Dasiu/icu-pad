package com.icupad.hl7_gateway.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Hl7Message extends BaseEntity {
    @Column(nullable = false)
    @Size(min = 1)
    @NotNull
    private String hl7Id;

    @Column(nullable = false, length = 65535) // arbitrary large value. it should be sufficient
    @Size(min = 1, max = 65535)
    @NotNull
    private String body;

    @Column(nullable = false)
    @NotNull
    private boolean processedCorrectly;

    public String getHl7Id() {
        return hl7Id;
    }

    public void setHl7Id(String hl7Id) {
        this.hl7Id = hl7Id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean wasProcessedCorrectly() {
        return processedCorrectly;
    }

    public void setProcessedCorrectly(boolean processedCorrectly) {
        this.processedCorrectly = processedCorrectly;
    }
}