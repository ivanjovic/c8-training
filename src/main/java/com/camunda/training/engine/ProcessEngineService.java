package com.camunda.training.engine;

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
}
