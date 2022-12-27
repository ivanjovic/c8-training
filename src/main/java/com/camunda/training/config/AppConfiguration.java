package com.camunda.training.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.operate.CamundaOperateClient;
import io.camunda.operate.auth.AuthInterface;
import io.camunda.operate.auth.SaasAuthentication;
import io.camunda.operate.exception.OperateException;
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

    @Bean
    public CamundaOperateClient operateClient(OperateProperties properties) throws OperateException {
        AuthInterface authentication = new SaasAuthentication(
                properties.getOauthUrl(),
                properties.getTokenAudience(),
                properties.getClientId(),
                properties.getClientSecret()
        );

        return new CamundaOperateClient.Builder()
                .operateUrl(properties.getBaseUrl())
                .authentication(authentication)
                .build();
    }
}
