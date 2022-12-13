package com.camunda.training.worker;

import com.camunda.training.config.AppProperties;
import com.camunda.training.config.ProcessVariableKeys;
import com.camunda.training.variables.InputData;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ValidateInputWorker {
    private final AppProperties properties;

    @JobWorker(type = "validateInput")
    public Map<String, Object> execute(final ActivatedJob job, @VariablesAsType @Validated InputData input) {

        if (properties.isDryRun() || input.isDryRun()) {
            return Map.of(ProcessVariableKeys.DRY_RUN, true);
        }

        return Map.of();
    }
}
