package com.camunda.training.controller;

import com.camunda.training.controller.dto.ProcessInstanceResponse;
import com.camunda.training.controller.dto.StartProcessRequest;
import com.camunda.training.engine.operate.OperateService;
import com.camunda.training.controller.dto.CalculationData;
import com.camunda.training.service.ProcessStarter;
import io.camunda.operate.dto.ProcessDefinition;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProcessRestController {

    private final ProcessStarter processStarter;
    private final OperateService operateService;

    @PostMapping("console-suggestion/start")
    public ResponseEntity<ProcessInstanceResponse> startConsoleSuggestion(
            @RequestParam(required = false) boolean dryRun,
            @RequestBody @Validated StartProcessRequest request) {
        ProcessInstanceEvent processInstanceEvent = processStarter.startConsoleSuggestionProcess(request, dryRun);

        return ResponseEntity.accepted()
                .body(
                        new ProcessInstanceResponse(
                                processInstanceEvent.getProcessInstanceKey(),
                                processInstanceEvent.getVersion()
                        )
                );
    }

    @PostMapping("calculation/start")
    public ResponseEntity<ProcessInstanceResponse> startCalculation(
            @RequestBody List<CalculationData> calculations) {
        ProcessInstanceEvent processInstanceEvent = processStarter.startCalculations(calculations);

        return ResponseEntity.accepted()
                .body(
                        new ProcessInstanceResponse(
                                processInstanceEvent.getProcessInstanceKey(),
                                processInstanceEvent.getVersion()
                        )
                );
    }

    @GetMapping("/definition/{bpmnProcessId}/latest")
    public ProcessDefinition getLatestProcessDefinition(@PathVariable String bpmnProcessId) {
        return operateService.getLastestProcessDefinition(bpmnProcessId);
    }
}
