package com.icupad.hl7_gateway.test_type_module.default_test_type.repository;

import com.icupad.hl7_gateway.core.repository.BaseRepository;
import com.icupad.hl7_gateway.test_type_module.default_test_type.domain.TestRequest;
import org.springframework.stereotype.Repository;

@Repository("defaultTestRequestRepository")
public interface TestRequestRepository extends BaseRepository<TestRequest, Long> {
}