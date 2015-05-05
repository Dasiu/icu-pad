package com.icupad.hl7_gateway.service;

import com.icupad.hl7_gateway.domain.RawTestName;
import com.icupad.hl7_gateway.repository.RawTestNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RawTestNameServiceImpl extends AbstractBaseService<RawTestName>
        implements RawTestNameService {
    private final RawTestNameRepository rawTestNameRepository;

    @Autowired
    public RawTestNameServiceImpl(RawTestNameRepository rawTestNameRepository) {
        super(rawTestNameRepository);

        this.rawTestNameRepository = rawTestNameRepository;
    }

    @Override
    public RawTestName findByRawName(String rawName) {
        return rawTestNameRepository.findByRawName(rawName);
    }

    @Override
    public RawTestName saveIfNotExists(RawTestName rawTestName) {
        RawTestName attachedRawTestName = findByRawName(rawTestName.getRawName());
        return (attachedRawTestName == null) ? save(rawTestName) : attachedRawTestName;
    }

    @Override
    public List<RawTestName> saveIfNotExists(Iterable<RawTestName> rawTestNames) {
        ArrayList<RawTestName> attachedRawTestNames = new ArrayList<>();
        for (RawTestName rawTestName : rawTestNames) {
            attachedRawTestNames.add(saveIfNotExists(rawTestName));
        }
        return attachedRawTestNames;
    }
}
