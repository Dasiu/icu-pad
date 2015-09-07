package com.icupad.common.provider;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public abstract class DataProvider {
	
	@Value("${debug.mode:true}")
	protected boolean debugMode;
	
	public void provideInitData() {
	}
	
	public void provideTestData() {
	}
	
	@PostConstruct
	public final void provide() {
		provideInitData();
		
		if (debugMode) {
			provideTestData();
		}
	}

}
