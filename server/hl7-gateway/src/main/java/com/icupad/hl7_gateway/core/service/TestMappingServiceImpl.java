package com.icupad.hl7_gateway.core.service;

import com.icupad.hl7_gateway.domain.TestMapping;
import com.icupad.hl7_gateway.core.repository.TestMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TestMappingServiceImpl extends AbstractBaseService<TestMapping>
        implements TestMappingService {
    private final TestMappingRepository testMappingRepository;

    @Autowired
    public TestMappingServiceImpl(TestMappingRepository testMappingRepository) {
        super(testMappingRepository);

        this.testMappingRepository = testMappingRepository;
    }

    @Override
    public TestMapping findByRawTestName(String rawTestName) {
        return testMappingRepository.findByRawTestName(rawTestName);
    }

    @Override
    @Transactional
    public TestMapping saveIfNotExistsByRawTestName(TestMapping testMapping) {
        TestMapping attachedTestMapping = findByRawTestName(testMapping.getRawTestName());
        return (attachedTestMapping == null) ? save(testMapping) : attachedTestMapping;
    }

    @Override
    @Transactional
    public List<TestMapping> saveIfNotExistsByRawTestName(Iterable<TestMapping> rawTestNames) {
        ArrayList<TestMapping> attachedTestMappings = new ArrayList<>();
        for (TestMapping testMapping : rawTestNames) {
            attachedTestMappings.add(saveIfNotExistsByRawTestName(testMapping));
        }
        return attachedTestMappings;
    }
}
