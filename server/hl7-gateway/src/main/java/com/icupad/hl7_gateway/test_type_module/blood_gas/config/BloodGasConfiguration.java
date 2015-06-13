package com.icupad.hl7_gateway.test_type_module.blood_gas.config;

import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.BloodSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BloodGasConfiguration {
    @Bean
    public BloodSource defaultBloodSource() {
        return BloodSource.ARTERY;
    }
}