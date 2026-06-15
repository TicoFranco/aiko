package com.test.aiko.dtos.response;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record EquipmentStateResponseDto(@NotBlank(message = "id is required") UUID id,
                                        @NotBlank(message = "name is required") String name,
                                        @NotBlank(message = "color is required") String color) {
}
