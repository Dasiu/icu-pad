package com.icupad.service;

import com.icupad.domain.nursing_process.ExecutedActivity;
import com.icupad.repository.ExecutedActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecutedActivityImpl extends AbstractBaseService<ExecutedActivity> implements ExecutedActivityService {
    private ExecutedActivityRepository executedActivityRepository;

    @Autowired
    public ExecutedActivityImpl(ExecutedActivityRepository executedActivityRepository) {
        super(executedActivityRepository);

        this.executedActivityRepository = executedActivityRepository;
    }
}
