package com.camunda.training.controller.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Value
@RequiredArgsConstructor
@Builder
public class StartProcessRequest {

    @NotNull
    @NotBlank
    String name;

    @NotNull
    LocalDate birthday;

    @Email
    @NotNull
    String email;
}
