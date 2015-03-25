package com.icupad.repository;

import com.icupad.domain.nursing_process.Activity;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends BaseRepository<Activity, Long> {
}
