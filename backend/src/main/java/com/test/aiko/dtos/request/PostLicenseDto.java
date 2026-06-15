package com.test.aiko.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record PostLicenseDto(@NotBlank(message = "user email is required") String email,
                             @NotBlank(message = "license is required") String license) {
}
