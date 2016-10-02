package com.icupad.hl7_gateway.core.service.hl7_server.handler.test_type_handler;

import com.icupad.hl7_gateway.domain.TestRequest;
import com.icupad.hl7_gateway.domain.TestResult;
import com.icupad.hl7_gateway.domain.TestType;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;

/**
 * Used to map intermediate models (TestRequest and TestResult) to test type specific domain models.
 *
 * Handler should handle situation when test results are send again (because previously results were incomplete)
 * and prevent duplication or data lose.
 */
public interface TestTypeHandler {
    void handle(Collection<Pair<TestRequest, TestResult>> testRequestsAndResults);

    Class<? extends TestType> getTestType();
}
