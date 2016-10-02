package com.icupad.hl7_gateway.core.service.hl7_server;

import ca.uhn.hl7v2.AcknowledgmentCode;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.Application;
import ca.uhn.hl7v2.app.ApplicationException;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.segment.MSH;
import com.icupad.hl7_gateway.domain.Hl7Message;
import com.icupad.hl7_gateway.core.service.Hl7MessageService;
import com.icupad.hl7_gateway.core.service.TestMappingService;
import com.icupad.hl7_gateway.core.service.hl7_server.handler.MessageHandler;
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
 * <p>
 * Handles common handlers operations like logging and exception handling.
 */
@Component
public class MessageDispatcher implements Application {
    private static final Logger logger = Logger.getLogger(MessageDispatcher.class);

    private final Map<Class<? extends Message>, MessageHandler<? extends Message>> handlers;
    private final Hl7MessageService hl7MessageService;
    private final TestMappingService testMappingService;

    @Autowired
    public MessageDispatcher(List<MessageHandler<? extends Message>> handlers,
                             Hl7MessageService hl7MessageService,
                             TestMappingService testMappingService) {
        this.testMappingService = testMappingService;
        this.handlers = handlers.stream().collect(Collectors.toMap(MessageHandler::getMessageType, h -> h));
        this.hl7MessageService = hl7MessageService;
    }

    /**
     * Method catches MissingTestNameMappingException in order to save raw test names for which mappings
     * are missing. This helps in detecting, which mappings are missing and in adding mappings to system.
     *
     * @param message received message
     * @return response (ACK). If error occur ACK with error status (CE) is send. There is one exception for that.
     * <p>
     * When HL7 gateway starts work, it may receive patient discharge (ADT^08) or details update (ADT^03) message
     * before receive patient registration (ADT^01) message. In that case these messages can not be processed correctly,
     * so PatientNotFoundException or StayNotFoundException are thrown, but still response (ACK) with success status
     * will be send back (CA). The reason for that is clients HL7 gateway, which does not expect error
     * in that situation, so to achieve seamless integration between gateways these exceptions are merely logged.
     * @throws ApplicationException
     * @throws HL7Exception
     */
    @Override
    public Message processMessage(Message message) throws ApplicationException, HL7Exception {
        Hl7Message messageEntity = createMessageEntity(message);
        try {
            dispatch(message.getClass().cast(message));
            ACK ack = generateACK(message);
            messageEntity.setProcessedCorrectly(true);

            return ack;
        } catch (MissingTestMappingException e) {
            logger.error(e);

            testMappingService.saveIfNotExistsByRawTestName(e.getTestMappings());
            return generateACK(message);
        } catch (PatientNotFoundException | StayNotFoundException e) {
            logger.error("Invalid message", e);

            return generateACK(message);
        } catch (RuntimeException e) {
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
    private <M extends Message> void dispatch(M message) throws HL7Exception {
        MessageHandler<M> handler = (MessageHandler<M>) handlers.get(message.getClass());
        handler.handle(message);
    }

    private ACK generateACK(Message msg) throws HL7Exception {
        try {
            ACK ack = (ACK) msg.generateACK();
            deleteTriggerEvent(ack);
            correctAcknowledgementCode(ack);

            logger.debug(ack);

            return ack;
        } catch (IOException e) {
            throw new RuntimeException(e); // translation to unchecked exception for convenience
        }
    }

    private void deleteTriggerEvent(ACK ack) throws DataTypeException {
        ack.getMSH().getMsh9_MessageType().getTriggerEvent().setValue(null);
    }

    private void correctAcknowledgementCode(ACK ack) throws DataTypeException {
        ack.getMSA().getAcknowledgementCode().setValue(AcknowledgmentCode.CA.toString());
    }
}
