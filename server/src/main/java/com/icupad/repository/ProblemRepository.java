package com.icupad.repository;

import com.icupad.domain.nursing_process.Problem;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends BaseRepository<Problem, Long> {
}
