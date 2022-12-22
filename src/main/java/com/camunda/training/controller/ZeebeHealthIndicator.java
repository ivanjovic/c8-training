package com.camunda.training.controller;

import com.camunda.training.engine.ProcessEngineService;
import io.camunda.zeebe.client.api.response.Topology;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ZeebeHealthIndicator implements HealthIndicator {

    private static final String HEALTH_KEY = "zeebe";

    private final ProcessEngineService processEngineService;

    @Override
    public Health health() {
        try {
            Topology topology = processEngineService.getTopologyDetails();
            return Health.up()
                    .withDetail(HEALTH_KEY, topology)
                    .build();
        } catch (Exception ex) {
            return Health.down()
                    .withDetail(HEALTH_KEY, ex)
                    .build();
        }
    }
}
