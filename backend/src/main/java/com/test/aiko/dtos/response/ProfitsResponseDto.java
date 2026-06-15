package com.test.aiko.dtos.response;

import jakarta.validation.constraints.NotBlank;

public record ProfitsResponseDto(@NotBlank(message = "date is required") String date,
                                         @NotBlank(message = "value is required") int value) {
}
