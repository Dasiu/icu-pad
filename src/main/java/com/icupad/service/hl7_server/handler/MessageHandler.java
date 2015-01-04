package com.icupad.service.hl7_server.handler;

import ca.uhn.hl7v2.app.Application;
import com.icupad.service.hl7_server.MessageType;
import com.icupad.service.hl7_server.TriggerEvent;

public interface MessageHandler extends Application {
    MessageType getMessageType();

    TriggerEvent getTriggerEvent();
}
