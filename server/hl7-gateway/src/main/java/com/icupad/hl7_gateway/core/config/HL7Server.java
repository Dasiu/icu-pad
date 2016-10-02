package com.icupad.hl7_gateway.core.config;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.ConnectionListener;
import ca.uhn.hl7v2.app.HL7Service;
import ca.uhn.hl7v2.llp.MinLowerLayerProtocol;
import ca.uhn.hl7v2.protocol.ReceivingApplicationExceptionHandler;
import com.icupad.hl7_gateway.domain.TestType;
import com.icupad.hl7_gateway.core.service.hl7_server.MessageDispatcher;
import com.icupad.hl7_gateway.core.service.hl7_server.UnsupportedTestTypeException;
import com.icupad.hl7_gateway.core.service.hl7_server.handler.test_type_handler.TestTypeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.nio.charset.Charset;
import java.util.List;
import java.util.function.Function;

@Configuration
public class HL7Server {
    @Value("${hl7_server.port}")
    private int port;

    @Value("${hl7_server.use_ssl}")
    private boolean useSSL;

    @Autowired
    private ConnectionListener connectionListener;

    @Autowired
    @Bean(initMethod = "startAndWait", destroyMethod = "stopAndWait")
    public HL7Service hl7Server(HapiContext hapiContext,
                                MessageDispatcher messageDispatcher,
                                ReceivingApplicationExceptionHandler exceptionHandler) {
        HL7Service server = hapiContext.newServer(port, useSSL);

        server.registerConnectionListener(connectionListener);

        server.registerApplication("*", "*", messageDispatcher);
        server.setExceptionHandler(exceptionHandler);

        return server;
    }

    @Autowired
    @Bean
    public HapiContext hapiContext(MinLowerLayerProtocol minLowerLayerProtocol) {
        HapiContext context = new DefaultHapiContext();
        context.setLowerLayerProtocol(minLowerLayerProtocol);
        context.getParserConfiguration().setDefaultObx2Type("ST");
        return context;
    }

    @Bean
    public MinLowerLayerProtocol minLowerLayerProtocol() {
        MinLowerLayerProtocol minLowerLayerProtocol = new MinLowerLayerProtocol(false);
        minLowerLayerProtocol.setCharset(Charset.forName("UTF-8"));
        return minLowerLayerProtocol;
    }

    @Autowired
    @Bean
    public Function<Class<? extends TestType>, TestTypeHandler> getTestTypeSpecificHandler(
            List<TestTypeHandler> testTypeHandlers) {
        return testType ->
                testTypeHandlers.stream()
                        .filter(handler -> handler.getTestType().equals(testType))
                        .findFirst()
                        .orElseThrow(() -> new UnsupportedTestTypeException(testType));
    }

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }


}