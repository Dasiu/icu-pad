package com.icupad.common.controller;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class RestDateFormat {
	
	public static final String PATTERN = "yyyyMMdd";
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
	
	public static String getPattern() {
		return PATTERN;
	}

	public static String format(TemporalAccessor date) {
		return formatter.format(date);
	}

}
