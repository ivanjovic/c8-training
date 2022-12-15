package com.camunda.training.engine.worker;

import com.camunda.training.engine.variables.InputData;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import io.camunda.zeebe.spring.client.exception.ZeebeBpmnError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidateInputWorker {

    private final Validator validator;

    @JobWorker(type = "validateInput")
    public void execute(final ActivatedJob job, @VariablesAsType InputData input) {
        Set<ConstraintViolation<InputData>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            String errorMessage = formatViolations(violations);
            log.error(errorMessage);
            throw new ZeebeBpmnError("INVALID_INPUT_ERROR", errorMessage);
        }
    }

    private String formatViolations(Set<ConstraintViolation<InputData>> violations) {
        return "Invalid input-data provided: " + violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
    }
}
