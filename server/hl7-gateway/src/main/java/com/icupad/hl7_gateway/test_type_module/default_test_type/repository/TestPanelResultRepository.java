package com.icupad.hl7_gateway.test_type_module.default_test_type.repository;

import com.icupad.hl7_gateway.core.domain.TestResultExecutor;
import com.icupad.hl7_gateway.core.repository.BaseRepository;
import com.icupad.hl7_gateway.test_type_module.default_test_type.domain.TestPanelResult;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository("defaultTestPanelResultRepository")
public interface TestPanelResultRepository extends BaseRepository<TestPanelResult, Long> {
    TestPanelResult findByRequestDateAndExecutor(LocalDateTime requestDate, TestResultExecutor executor);
}