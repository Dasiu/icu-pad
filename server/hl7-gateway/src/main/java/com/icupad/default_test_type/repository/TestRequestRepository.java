package com.icupad.default_test_type.repository;

import com.icupad.hl7_gateway.repository.BaseRepository;
import com.icupad.default_test_type.domain.TestRequest;
import org.springframework.stereotype.Repository;

@Repository("defaultTestRequestRepository")
public interface TestRequestRepository extends BaseRepository<TestRequest, Long> {
    TestRequest findByHl7Id(String hl7Id);
}