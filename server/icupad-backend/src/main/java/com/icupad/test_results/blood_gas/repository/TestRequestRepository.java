package com.icupad.test_results.blood_gas.repository;

import com.icupad.repository.BaseRepository;
import com.icupad.test_results.blood_gas.domain.TestRequest;
import org.springframework.stereotype.Repository;

@Repository("bloodGasTestRequestRepository")
public interface TestRequestRepository extends BaseRepository<TestRequest, Long> {
    TestRequest findByHl7Id(String hl7Id);
}