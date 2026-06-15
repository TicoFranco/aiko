package com.test.aiko.dtos.request;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record EquipmentPositionHistoryDto(@NotBlank(message = "lon is required") double lon,
                                          @NotBlank(message = "lat is required") double lat,
                                          @NotBlank(message = "equipment id is required") UUID equipment_id) {
}
