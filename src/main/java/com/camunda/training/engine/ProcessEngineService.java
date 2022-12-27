package com.camunda.training.engine;

import com.camunda.training.controller.dto.StartProcessRequest;
import com.camunda.training.engine.variables.InputData;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.client.api.response.Topology;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessEngineService {

    private final ZeebeClient zeebeClient;

    public Topology getTopologyDetails() {
        return zeebeClient.newTopologyRequest()
                .send()
                .join();
    }

    public ProcessInstanceEvent startProcessInstance(String bpmProcessId, Object vars) {
        return zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(bpmProcessId)
                .latestVersion()
                .variables(vars)
                .send()
                .join();
    }

    private InputData toInputData(StartProcessRequest request, boolean dryRun) {
        return InputData.builder()
                .name(request.getName())
                .birthday(request.getBirthday())
                .email(request.getEmail())
                .dryRun(dryRun)
                .build();
    }
}
