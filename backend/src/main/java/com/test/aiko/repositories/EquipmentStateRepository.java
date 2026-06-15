package com.test.aiko.repositories;

import com.test.aiko.models.EquipmentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipmentStateRepository extends JpaRepository<EquipmentState, UUID> {
}
