package com.test.aiko.dtos.response;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record EquipmentModelResponseDto(@NotBlank(message = "id is required") UUID id,
                                        @NotBlank(message = "name is required") String name) {
}
