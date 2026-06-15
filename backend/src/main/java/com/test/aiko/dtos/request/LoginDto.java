package com.test.aiko.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(@NotBlank(message = "email is required") String email,@NotBlank(message = "password is required") String password) {
}
