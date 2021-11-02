package com.wu.ordersystem.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wujianxin
 * @date 2021-09-26
 * @description 跨域处理器
 */

@Configuration
public class CustomizeSerializerConfig {

    @Value("${spring.jackson.datetime-format}")
    private String dateTimePattern;

    @Value("${spring.jackson.date-format}")
    private String datePattern;

    @Value("${spring.jackson.time-format}")
    private String timePattern;
//    private static final String timePattern = "HH:mm:ss";

    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimePattern));
    }

    @Bean
    public LocalDateSerializer localDateSerializer() {
        return new LocalDateSerializer(DateTimeFormatter.ofPattern(datePattern));
    }

    @Bean
    public LocalTimeSerializer localTimeSerializer() {
        return new LocalTimeSerializer(DateTimeFormatter.ofPattern(timePattern));
    }

    @Bean
    public JsonDeserializer<LocalDateTime> localDateTimeJsonDeserializer() {
        return new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return LocalDateTime.parse(jsonParser.getText(), DateTimeFormatter.ofPattern(dateTimePattern));
            }
        };
    }

    @Bean
    public JsonDeserializer<LocalDate> localDateJsonDeserializer() {
        return new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return LocalDate.parse(jsonParser.getText(), DateTimeFormatter.ofPattern(datePattern));
            }
        };
    }

    @Bean
    public JsonDeserializer<LocalTime> localTimeJsonDeserializer() {
        return new JsonDeserializer<LocalTime>() {
            @Override
            public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return LocalTime.parse(jsonParser.getText(), DateTimeFormatter.ofPattern(timePattern));
            }
        };
    }

    @Bean("objectMapper")
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
            builder.serializerByType(LocalDate.class, localDateSerializer());
            builder.serializerByType(LocalTime.class, localTimeSerializer());
            builder.deserializerByType(LocalDateTime.class, localDateTimeJsonDeserializer());
            builder.deserializerByType(LocalDate.class, localDateJsonDeserializer());
            builder.deserializerByType(LocalTime.class, localTimeJsonDeserializer());
        };
    }
}
