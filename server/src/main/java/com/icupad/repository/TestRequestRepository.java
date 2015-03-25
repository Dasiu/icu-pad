package com.icupad.repository;

import com.icupad.domain.TestRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRequestRepository extends BaseRepository<TestRequest, Long> {
    TestRequest findByHl7Id(String hl7Id);
}
