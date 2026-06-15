package com.test.aiko.dtos.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record PutUserDto(@NotBlank String email, @Nullable String password,@Nullable String username) {
}
