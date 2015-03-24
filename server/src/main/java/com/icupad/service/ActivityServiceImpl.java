package com.icupad.service;

import com.icupad.domain.nursing_process.Activity;
import com.icupad.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends AbstractBaseService<Activity> implements ActivityService {
    private ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        super(activityRepository);

        this.activityRepository = activityRepository;
    }
}
