package com.camunda.training.engine.worker;

import com.camunda.training.config.ProcessConstants;
import com.camunda.training.engine.variables.InputData;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.camunda.training.config.ConsoleDefinition.CONSOLES;

@Component
@Slf4j
public class EvaluateConsoleWorker {

    @JobWorker
    public Map<String, List<String>> evaluateConsole(@VariablesAsType InputData data) {

        int birthyear = data.getBirthday().getYear();

        List<String> suggestions = CONSOLES.entrySet()
                .stream()
                .filter(e -> e.getKey().contains(birthyear))
                .map(Map.Entry::getValue)
                .toList();

        /*
         * we need a label, value object because these are used with a Select form-field
         * see: https://docs.camunda.io/docs/next/components/modeler/forms/configuration/forms-config-options/
         */
        return Map.of(ProcessConstants.VK_SUGGESTIONS, suggestions);
    }
}
