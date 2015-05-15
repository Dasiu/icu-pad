package com.icupad.hl7_gateway.core.service;

import com.icupad.hl7_gateway.core.domain.TestType;
import com.icupad.hl7_gateway.core.repository.TestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestTypeServiceImpl extends AbstractBaseService<TestType>
        implements TestTypeService {
    private final TestTypeRepository testTypeRepository;

    @Autowired
    public TestTypeServiceImpl(TestTypeRepository testTypeRepository) {
        super(testTypeRepository);

        this.testTypeRepository = testTypeRepository;
    }

    @Override
    public TestType findByName(String name) {
        return testTypeRepository.findByName(name);
    }
}
