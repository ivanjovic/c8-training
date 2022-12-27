package com.camunda.training.engine.worker;

import com.camunda.training.controller.dto.CalculationData;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.util.Map;

import static com.camunda.training.config.ProcessConstants.CALC_RESULT;

@Component
@RequiredArgsConstructor
@Slf4j
public class CalculateWorker {

    @JobWorker
    public Map<String, Object> calculate(final ActivatedJob job, @Variable CalculationData calculation) {

        switch (calculation.getOperation()) {
            case ADD -> {
                return Map.of(CALC_RESULT, calculation.getFirst().add(calculation.getSecond()));
            }
            case SUBTRACT -> {
                return Map.of(CALC_RESULT, calculation.getFirst().subtract(calculation.getSecond()));
            }
            case MULTIPLY -> {
                return Map.of(CALC_RESULT, calculation.getFirst().multiply(calculation.getSecond()));
            }
            case DIVIDE -> {
                return Map.of(CALC_RESULT, calculation.getFirst().divide(calculation.getSecond(), RoundingMode.HALF_DOWN));
            }
            default -> throw new IllegalArgumentException("Unknown Math-Operand");
        }
    }
}
