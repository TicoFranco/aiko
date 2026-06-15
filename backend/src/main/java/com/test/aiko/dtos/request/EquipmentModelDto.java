package com.test.aiko.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record EquipmentModelDto(@NotBlank(message = "name is required") String name) {
}
