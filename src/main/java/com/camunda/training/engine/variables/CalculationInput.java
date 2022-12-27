package com.camunda.training.engine.variables;

import com.camunda.training.controller.dto.CalculationData;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor
public class CalculationInput {

    List<CalculationData> calculations;
}
