package com.test.aiko.dtos.response;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ProfitsTimelineResponseDto(@NotBlank(message = "equipment is required") EquipmentResponseDto equipment ,
                                         @NotBlank(message = "profitsTimeline is required") List<ProfitsResponseDto> profits) {
}
