package com.icupad.service;

import com.icupad.domain.nursing_process.ProblemOccurenece;
import com.icupad.repository.ProblemOccurrenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemOccurrenceServiceImpl extends AbstractBaseService<ProblemOccurenece>
        implements ProblemOccurrenceService {
    private ProblemOccurrenceRepository problemOccurrenceRepository;

    @Autowired
    public ProblemOccurrenceServiceImpl(ProblemOccurrenceRepository problemOccurrenceRepository) {
        super(problemOccurrenceRepository);

        this.problemOccurrenceRepository = problemOccurrenceRepository;
    }
}
