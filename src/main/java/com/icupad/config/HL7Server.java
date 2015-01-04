package com.icupad.config;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.ConnectionListener;
import ca.uhn.hl7v2.app.HL7Service;
import com.icupad.service.hl7_server.handler.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HL7Server {
    @Value("${hl7_server.port}")
    private int port;

    @Value("${hl7_server.use_ssl}")
    private boolean useSSL;

    @Autowired
    private List<MessageHandler> handlers;

    @Autowired
    private ConnectionListener connectionListener;

    @Bean(initMethod = "startAndWait", destroyMethod = "stopAndWait")
    public HL7Service hl7Server() {
        HapiContext context = new DefaultHapiContext();
        HL7Service server = context.newServer(port, useSSL);

        server.registerConnectionListener(connectionListener);

        registerHandlers(server);

        return server;
    }

    private void registerHandlers(HL7Service server) {
        handlers.forEach(handler ->
                        server.registerApplication(handler.getMessageType().toString(),
                                handler.getTriggerEvent().toString(),
                                handler)
        );
    }
}