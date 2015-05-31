package com.icupad.test_results.complete_blood_count.repository;

import com.icupad.repository.BaseRepository;
import com.icupad.test_results.complete_blood_count.domain.TestRequest;
import org.springframework.stereotype.Repository;

@Repository("completeBloodCountTestRequestRepository")
public interface TestRequestRepository extends BaseRepository<TestRequest, Long> {
    TestRequest findByHl7Id(String hl7Id);
}