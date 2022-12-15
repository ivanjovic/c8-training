package com.camunda.training.engine.worker;

import com.camunda.training.engine.variables.InputData;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidateInputWorker {

    @JobWorker(type = "validateInput")
    public void execute(final ActivatedJob job, @VariablesAsType @Valid InputData input) {
        log.info("Passing validateInput");
    }
}
