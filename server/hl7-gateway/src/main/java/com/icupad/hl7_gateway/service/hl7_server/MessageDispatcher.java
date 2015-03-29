package com.icupad.hl7_gateway.service.hl7_server;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.Application;
import ca.uhn.hl7v2.app.ApplicationException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.segment.MSH;
import com.icupad.hl7_gateway.domain.Hl7Message;
import com.icupad.hl7_gateway.service.Hl7MessageService;
import com.icupad.hl7_gateway.service.hl7_server.handler.MessageHandler;
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
    private final Hl7MessageService hl7MessageService;

    @Autowired
    public MessageDispatcher(List<MessageHandler<? extends Message>> handlers, Hl7MessageService hl7MessageService) {
        this.handlers = handlers.stream().collect(Collectors.toMap(h -> h.getMessageType(), h -> h));
        this.hl7MessageService = hl7MessageService;
    }

    @Override
    public Message processMessage(Message message) throws ApplicationException, HL7Exception {
        Hl7Message messageEntity = createMessageEntity(message);
        try {
            ACK ack = dispatch(message.getClass().cast(message));
            messageEntity.setProcessedCorrectly(true);

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
        } finally {
            hl7MessageService.save(messageEntity);
        }
    }

    @Override
    public boolean canProcess(Message message) {
        logger.debug(message);

        return handlers.keySet().contains(message.getClass());
    }

    private Hl7Message createMessageEntity(Message message) throws HL7Exception {
        Hl7Message messageEntity = new Hl7Message();
        String messageId = getMessageId(message);
        messageEntity.setHl7Id(messageId);
        messageEntity.setBody(message.toString());
        return messageEntity;
    }

    private String getMessageId(Message message) throws HL7Exception {
        return ((MSH) message.get("MSH")).getMessageControlID().getValue();
    }

    @SuppressWarnings("unchecked")
    private <M extends Message> ACK dispatch(M message) throws IOException, HL7Exception {
        MessageHandler<M> handler = (MessageHandler<M>) handlers.get(message.getClass());
        return handler.handle(message);
    }
}
