package com.camunda.training.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class ProcessDefinitionNotDeployedException extends AbstractThrowableProblem {

    private static final URI TYPE = URI.create("https://camunda.operate.com/not-found");

    public ProcessDefinitionNotDeployedException(String bpmProcessId) {
        super(
                TYPE,
                "Not found",
                Status.NOT_FOUND,
                String.format("Procss-Definition '%s' not found", bpmProcessId));
    }

}
