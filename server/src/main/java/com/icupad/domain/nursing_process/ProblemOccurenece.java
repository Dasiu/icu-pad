package com.icupad.domain.nursing_process;

import com.icupad.domain.test_result.TestResult;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ProblemOccurenece extends TestResult {
    @NotNull
    @ManyToOne
    private Problem problem;

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
