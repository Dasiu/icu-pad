package com.icupad.domain.test_result;

import javax.persistence.Entity;

@Entity
public class HemodynamicsResult extends TestResult {
    private Integer hr;
    private String abp;
}
