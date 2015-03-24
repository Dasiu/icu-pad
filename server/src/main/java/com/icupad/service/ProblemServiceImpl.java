package com.icupad.service;

import com.icupad.domain.nursing_process.Problem;
import com.icupad.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemServiceImpl extends AbstractBaseService<Problem> implements ProblemService {
    private ProblemRepository problemRepository;

    @Autowired
    public ProblemServiceImpl(ProblemRepository problemRepository) {
        super(problemRepository);

        this.problemRepository = problemRepository;
    }
}
