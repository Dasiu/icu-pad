package com.icupad.nurse.provider;

import com.icupad.common.provider.DataProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NurseDataProvider extends DataProvider {

	private NurseFunctionProvider nurseFunctionProvider;

	@Autowired
	public NurseDataProvider(NurseFunctionProvider nurseFunctionProvider) {
		this.nurseFunctionProvider = nurseFunctionProvider;
	}

	@Override
	public void provideInitData() {
		nurseFunctionProvider.provide();
	}
	
}
