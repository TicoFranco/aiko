package com.test.aiko.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record EquipmentStateDto(@NotBlank(message = "name is required") String name,
                                @NotBlank(message = "color is required") String color) {
}
