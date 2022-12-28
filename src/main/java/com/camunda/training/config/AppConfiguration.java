package com.camunda.training.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.zeebe.client.api.JsonMapper;
import io.camunda.zeebe.client.impl.ZeebeObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public JsonMapper jsonMapper(final ObjectMapper objectMapper) {
        return new ZeebeObjectMapper(objectMapper);
    }
}
