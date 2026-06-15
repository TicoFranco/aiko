package com.test.aiko.dtos.response;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record EquipmentPositionHistoryResponseDto(@NotBlank(message = "id is required") UUID id,
                                                  @NotBlank(message = "local date time is required")LocalDateTime localDateTime,
                                                  @NotBlank(message = "lat is required")double lat,
                                                  @NotBlank(message = "lon is required")double lon) {
}
