package com.camunda.training.controller;

import com.camunda.training.controller.dto.ProcessInstanceResponse;
import com.camunda.training.controller.dto.StartProcessRequest;
import com.camunda.training.engine.ProcessEngineService;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProcessRestController {

    private final ProcessEngineService engineService;

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
}
