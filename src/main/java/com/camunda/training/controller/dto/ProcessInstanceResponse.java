package com.camunda.training.controller.dto;

public record ProcessInstanceResponse(long processInstanceKey, int version) {
}
