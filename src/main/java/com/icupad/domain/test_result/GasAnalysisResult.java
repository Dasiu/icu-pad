package com.icupad.domain.test_result;

import javax.persistence.Entity;

@Entity
public class GasAnalysisResult extends TestResult {
    private BloodSource bloodSource;
    private Double ph;
    private Double pCo2;
}
