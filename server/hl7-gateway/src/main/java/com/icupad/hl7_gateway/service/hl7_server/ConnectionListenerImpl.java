package com.icupad.hl7_gateway.service.hl7_server;

import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.ConnectionListener;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ConnectionListenerImpl implements ConnectionListener {
    private static final Logger logger = Logger.getLogger(ConnectionListenerImpl.class);

    public void connectionReceived(Connection theC) {
        logger.debug("New connection received: " + theC.getRemoteAddress().toString());
    }

    public void connectionDiscarded(Connection theC) {
        logger.debug("Lost connection from: " + theC.getRemoteAddress().toString());
    }
}
