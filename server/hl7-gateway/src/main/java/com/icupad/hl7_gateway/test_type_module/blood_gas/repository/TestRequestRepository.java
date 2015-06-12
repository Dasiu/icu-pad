package com.icupad.hl7_gateway.test_type_module.blood_gas.repository;

import com.icupad.hl7_gateway.core.repository.BaseRepository;
import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.TestRequest;
import org.springframework.stereotype.Repository;

@Repository("bloodGasTestRequestRepository")
public interface TestRequestRepository extends BaseRepository<TestRequest, Long> {
}