package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.TestGroup;
import com.icupad.hl7_gateway.repository.TestGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestGroupServiceImpl extends AbstractBaseService<TestGroup>
        implements TestGroupService {
    private final TestGroupRepository testGroupRepository;

    @Autowired
    public TestGroupServiceImpl(TestGroupRepository testGroupRepository) {
        super(testGroupRepository);

        this.testGroupRepository = testGroupRepository;
    }

    @Override
    public TestGroup findByName(String name) {
        return testGroupRepository.findByName(name);
    }

    @Override
    public TestGroup getDefaultGroup() {
        return findByName("Pozostałe wyniki");
    }
}
