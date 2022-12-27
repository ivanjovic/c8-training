package com.camunda.training.service;

import com.camunda.training.controller.dto.StartProcessRequest;
import com.camunda.training.engine.ProcessEngineService;
import com.camunda.training.controller.dto.CalculationData;
import com.camunda.training.engine.variables.CalculationInput;
import com.camunda.training.engine.variables.InputData;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.camunda.training.config.ProcessConstants.CALCULATION_PROCESS_ID;
import static com.camunda.training.config.ProcessConstants.CONSOLE_SUGGESTION_PROCESS_ID;

@Service
@RequiredArgsConstructor
public class ProcessStarter {

    private final ProcessEngineService engineService;

    public ProcessInstanceEvent startConsoleSuggestionProcess(StartProcessRequest request, boolean dryRun) {
        InputData vars = InputData.builder()
                .name(request.getName())
                .birthday(request.getBirthday())
                .email(request.getEmail())
                .dryRun(dryRun)
                .build();

        return engineService.startProcessInstance(CONSOLE_SUGGESTION_PROCESS_ID, vars);
    }

    public ProcessInstanceEvent startCalculations(List<CalculationData> data) {
        return engineService.startProcessInstance(CALCULATION_PROCESS_ID, new CalculationInput(data));
    }

}
