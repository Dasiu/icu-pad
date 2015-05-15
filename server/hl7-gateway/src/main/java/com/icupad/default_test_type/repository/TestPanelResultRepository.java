package com.icupad.default_test_type.repository;

import com.icupad.hl7_gateway.domain.TestResultExecutor;
import com.icupad.hl7_gateway.repository.BaseRepository;
import com.icupad.default_test_type.domain.TestPanelResult;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository("defaultTestPanelResultRepository")
public interface TestPanelResultRepository extends BaseRepository<TestPanelResult, Long> {
    TestPanelResult findByRequestDateAndExecutor(LocalDateTime requestDate, TestResultExecutor executor);
}