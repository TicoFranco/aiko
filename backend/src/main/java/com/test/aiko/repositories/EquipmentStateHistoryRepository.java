package com.test.aiko.repositories;

import com.test.aiko.models.EquipmentStateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipmentStateHistoryRepository extends JpaRepository<EquipmentStateHistory, UUID> {
}
