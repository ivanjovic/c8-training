package com.camunda.training.worker;

import com.camunda.training.config.ProcessVariableKeys;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class EvaluateConsoleWorker {

    @JobWorker
    public Map<String, Object> evaluateConsole() {
        return Map.of(ProcessVariableKeys.SUGGESTIONS, List.of());
    }
}
