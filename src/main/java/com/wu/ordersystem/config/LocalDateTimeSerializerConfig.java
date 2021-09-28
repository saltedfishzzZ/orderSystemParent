package com.wu.ordersystem.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author <a href="mailto:wujianxin@corp.51xiaodou.com">wujianxin</a>
 * @name BaseService CopyRright (c) 2021 by
 * <a href="mailto:wujianxin@corp.51xiaodou.com">wujianxin</a>
 * @date 2021年09月26日
 * @description
 */

@Configuration
public class LocalDateTimeSerializerConfig {

    @Value("${spring.jackson.date-format}")
    private String datePattern;

    private static final String timePattern = "HH:mm:ss";

    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(datePattern));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2LocalDateTimeMapperBuilderCustomizer() {
        return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
    }

    @Bean
    public LocalTimeSerializer localTimeSerializer() {
        return new LocalTimeSerializer(DateTimeFormatter.ofPattern(timePattern));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2LocalTimeMapperBuilderCustomizer() {
        return builder -> builder.serializerByType(LocalTime.class, localTimeSerializer());
    }
}
