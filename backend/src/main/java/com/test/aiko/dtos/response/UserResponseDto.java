package com.test.aiko.dtos.response;

import com.test.aiko.models.Equipment;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserResponseDto(@NotBlank(message = "username is required") String username,
                              @NotBlank(message = "email is required") String email,
                              @NotBlank(message = "equipmentList is required") List<Equipment> equipmentList) {
}
