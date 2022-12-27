package com.camunda.training.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "operate.client.cloud")
@Data
@NoArgsConstructor
public class OperateProperties {

    private String baseUrl;
    private String clusterId;
    private String clusterRegion;
    private String clientId;
    private String clientSecret;
    private String oauthUrl;
    private String tokenAudience;
}
