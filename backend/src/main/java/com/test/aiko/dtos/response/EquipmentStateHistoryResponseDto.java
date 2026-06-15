package com.test.aiko.dtos.response;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record EquipmentStateHistoryResponseDto(@NotBlank(message = "id is required") UUID id,
                                               @NotBlank(message = "equipment state is required") EquipmentStateResponseDto equipmentState,
                                               @NotBlank(message = "local date time is required") LocalDateTime localDateTime) {
}
