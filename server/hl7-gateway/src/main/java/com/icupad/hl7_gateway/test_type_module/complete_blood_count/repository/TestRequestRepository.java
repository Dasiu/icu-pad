package com.icupad.hl7_gateway.test_type_module.complete_blood_count.repository;

import com.icupad.hl7_gateway.core.repository.BaseRepository;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.TestRequest;
import org.springframework.stereotype.Repository;

@Repository("completeBloodCountTestRequestRepository")
public interface TestRequestRepository extends BaseRepository<TestRequest, Long> {
}