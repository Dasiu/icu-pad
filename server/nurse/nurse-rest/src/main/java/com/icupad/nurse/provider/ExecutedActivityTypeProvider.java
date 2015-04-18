package com.icupad.nurse.provider;

import com.icupad.common.provider.DataProvider;
import com.icupad.nurse.model.ExecutedActivityType;
import com.icupad.nurse.repository.ExecutedActivityTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExecutedActivityTypeProvider extends DataProvider {

	private final ExecutedActivityTypeRepository executedActivityTypeRepository;
	
	@Autowired
	public ExecutedActivityTypeProvider(ExecutedActivityTypeRepository executedActivityTypeRepository) {
		this.executedActivityTypeRepository = executedActivityTypeRepository;
	}

	@Override
	public void provideInitData() {
		executedActivityTypeRepository.save(new ExecutedActivityType("X", "pielęgniarka"));
		executedActivityTypeRepository.save(new ExecutedActivityType("F", "fizjoterapeuta"));
		executedActivityTypeRepository.save(new ExecutedActivityType("S", "samoopieka (pacjent)"));
		executedActivityTypeRepository.save(new ExecutedActivityType("R", "rodzic"));
	}
	
	

}
