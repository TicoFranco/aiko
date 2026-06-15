package com.test.aiko.dtos.request;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record EquipmentStateHistoryDto(@NotBlank(message = "equipment id is required") UUID equipment_id,
                                       @NotBlank(message = "equipment state id is required") UUID equipment_state_id) {
}
