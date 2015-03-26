package com.icupad.service.hl7_server;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.Application;
import ca.uhn.hl7v2.app.ApplicationException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v23.message.ACK;
import com.icupad.service.hl7_server.handler.MessageHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Receives all types of messages and dispatches them, if possible, to concrete messages handlers.
 * <p>
 * Allows generic handlers implementations.
 * Handles common handlers operations like logging and exception handling.
 */
@Component
public class MessageDispatcher implements Application {
    private static final Logger logger = Logger.getLogger(MessageDispatcher.class);

    private final Map<Class<? extends Message>, MessageHandler<? extends Message>> handlers;

    @Autowired
    public MessageDispatcher(List<MessageHandler<? extends Message>> handlers) {
        this.handlers = handlers.stream().collect(Collectors.toMap(h -> h.getMessageType(), h -> h));
    }

    @Override
    public Message processMessage(Message message) throws ApplicationException, HL7Exception {
        try {
            Class<? extends Message> messageType = message.getClass();
            ACK ack = dispatch(messageType.cast(message));

            logger.debug(ack);

            return ack;
        } catch (StayNotFoundException e) {
            logger.error("Invalid message", e);

            throw new HL7Exception(e);
        } catch (IOException | RuntimeException e) {
            logger.error("Internal error", e);

            // exception message and stack trace should not be included in ACK, it might contains sensitive data,
            // that is why exception is rethrown
            throw new ApplicationException("Internal error");
        }
    }

    @Override
    public boolean canProcess(Message message) {
        logger.debug(message);

        return handlers.keySet().contains(message.getClass());
    }

    @SuppressWarnings("unchecked")
    private <M extends Message> ACK dispatch(M message) throws IOException, HL7Exception {
        MessageHandler<M> handler = (MessageHandler<M>) handlers.get(message.getClass());
        return handler.handle(message);
    }
}
