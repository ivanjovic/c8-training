package com.camunda.training;

import io.camunda.zeebe.process.test.api.ZeebeTestEngine;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import io.camunda.zeebe.spring.test.ZeebeSpringTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ZeebeSpringTest
@Deployment(resources = {"classpath:c8-education.bpmn", "console-suggestions.dmn"})
public abstract class ZeebeIntegrationTest {

    @Autowired
    protected ZeebeTestEngine testEngine;

}
