package com.icupad.config;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.ConnectionListener;
import ca.uhn.hl7v2.app.HL7Service;
import ca.uhn.hl7v2.llp.MinLowerLayerProtocol;
import ca.uhn.hl7v2.protocol.ReceivingApplicationExceptionHandler;
import com.icupad.service.hl7_server.MessageDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public HL7Service hl7Server(MessageDispatcher messageDispatcher,
                                ReceivingApplicationExceptionHandler exceptionHandler) {
        HapiContext context = new DefaultHapiContext();
        context.setLowerLayerProtocol(new MinLowerLayerProtocol(true));
        HL7Service server = context.newServer(port, useSSL);

        server.registerConnectionListener(connectionListener);

        server.registerApplication("*", "*", messageDispatcher);
        server.setExceptionHandler(exceptionHandler);

        return server;
    }
}