package com.test.aiko.dtos.response;

import jakarta.annotation.Nullable;
import java.util.UUID;

public record EquipmentOwnerResponseDto(@Nullable UUID id,@Nullable String email,@Nullable String username){
}
