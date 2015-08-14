package com.icupad.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateTimeSerializerModule extends SimpleModule {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LocalDateTimeSerializerModule() {
        super("localDateTimeSerializer", new Version(0, 1, 0, "alpha"));
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    }

    public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(value.format(formatter));
        }
    }

    public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
                JsonProcessingException {
            String localDateTimeString = jp.getValueAsString();
            return LocalDateTime.parse(localDateTimeString, formatter);
        }
    }
}
