package com.icupad.hl7_gateway.repository;

import com.icupad.hl7_gateway.domain.TestRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRequestRepository extends BaseRepository<TestRequest, Long> {
    TestRequest findByHl7Id(String hl7Id);
}
