package com.test.aiko.dtos.response;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDto(@NotBlank String token) {
}
