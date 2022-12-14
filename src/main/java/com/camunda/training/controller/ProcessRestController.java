package com.camunda.training.controller;

import com.camunda.training.controller.dto.ProcessInstanceResponse;
import com.camunda.training.controller.dto.StartProcessRequest;
import com.camunda.training.engine.ProcessEngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProcessRestController {

    private final ProcessEngineService engineService;

    @PostMapping("start")
    public ResponseEntity<ProcessInstanceResponse> start(
            @RequestParam(required = false) boolean dryRun,
            @RequestBody @Validated StartProcessRequest request) {
        ProcessInstanceResponse response = engineService.startProcessInstance(request, dryRun);

        return ResponseEntity.accepted()
                .body(response);
    }
}
