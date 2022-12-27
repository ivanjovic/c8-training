package com.camunda.training.engine.variables;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PrintWorker {

    @JobWorker
    public void print(final ActivatedJob job) {
        log.info("Variables found in current job: {}", job.getVariablesAsMap());
    }
}
