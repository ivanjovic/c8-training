package com.camunda.training.variables;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@RequiredArgsConstructor
@Builder
@With
public class InputData {

    @NotNull
    @NotBlank
    String name;

    @Email
    String email;

    boolean dryRun;
}
