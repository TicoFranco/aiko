package com.test.aiko.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterDto(@NotBlank(message = "email is required") @Email
                          String email,
                          @NotBlank(message = "password is required")
                          @Size(min = 4,message = "password must be at least 4 characters")
                          @Pattern(regexp = "(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}", message = "invalid password format")
                          String password,
                          @NotBlank(message = "username is required")
                          @Pattern(regexp = "[A-Za-z][A-Za-z0-9\\\\-]*",message = "invalid username format")
                          @Size(min = 3, max = 30, message = "username must have between 3 and 30 characters")
                          String username) {
}
