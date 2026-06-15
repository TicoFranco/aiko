package com.test.aiko.dtos.request;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record EquipmentDto(@NotBlank(message = "name is required") String name,
                           @NotBlank(message = "model id is required") UUID model_id,
                           @NotBlank(message = "license is required") String license) {
}
