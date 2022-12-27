package com.camunda.training.controller;

import com.camunda.training.controller.dto.ProcessInstanceResponse;
import com.camunda.training.controller.dto.StartProcessRequest;
import com.camunda.training.engine.ProcessEngineService;
import com.camunda.training.engine.operate.OperateService;
import io.camunda.operate.dto.ProcessDefinition;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProcessRestController {

    private final ProcessEngineService engineService;
    private final OperateService operateService;

    @PostMapping("start")
    public ResponseEntity<ProcessInstanceResponse> start(
            @RequestParam(required = false) boolean dryRun,
            @RequestBody @Validated StartProcessRequest request) {
        ProcessInstanceEvent processInstanceEvent = engineService.startProcessInstance(request, dryRun);

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
