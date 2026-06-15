package com.test.aiko.dtos.response;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record EquipmentResponseDto(@NotBlank(message = "id is required") UUID id,
                                   @NotBlank(message = "name is required") String name,
                                   @NotBlank(message = "license is required") String license) {
}
