package com.camunda.training.engine;

import com.camunda.training.config.ProcessConstants;
import com.camunda.training.controller.dto.ProcessInstanceResponse;
import com.camunda.training.controller.dto.StartProcessRequest;
import com.camunda.training.engine.variables.InputData;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessEngineService {

    private final ZeebeClient zeebeClient;

    public ProcessInstanceResponse startProcessInstance(StartProcessRequest request, boolean dryRun) {
        ProcessInstanceEvent processInstanceEvent = zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(ProcessConstants.PROCESS_ID)
                .latestVersion()
                .variables(toInputData(request, dryRun))
                .send()
                .join();

        return new ProcessInstanceResponse(
                processInstanceEvent.getProcessInstanceKey(),
                processInstanceEvent.getVersion()
        );
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