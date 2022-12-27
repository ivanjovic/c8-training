package com.camunda.training.controller.dto;

import com.camunda.training.engine.variables.MathOperation;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@RequiredArgsConstructor
@Builder
public class CalculationData {

    BigDecimal first;
    BigDecimal second;
    MathOperation operation;
}
