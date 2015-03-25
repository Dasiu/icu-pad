package com.icupad.service;

import com.icupad.domain.nursing_process.Function;
import com.icupad.repository.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionServiceImpl extends AbstractBaseService<Function> implements FunctionService {
    private FunctionRepository functionRepository;

    @Autowired
    public FunctionServiceImpl(FunctionRepository functionRepository) {
        super(functionRepository);

        this.functionRepository = functionRepository;
    }
}
