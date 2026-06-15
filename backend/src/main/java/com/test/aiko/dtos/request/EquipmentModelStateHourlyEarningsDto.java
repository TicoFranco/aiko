package com.test.aiko.dtos.request;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record EquipmentModelStateHourlyEarningsDto(
        @NotBlank(message = "equipment model id is required") UUID equipment_model_id,
        @NotBlank(message = "equipment state id is required") UUID equipment_state_id,
        @NotBlank(message = "value is required") int value){
}
