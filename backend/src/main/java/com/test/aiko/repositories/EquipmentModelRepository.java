package com.test.aiko.repositories;

import com.test.aiko.models.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipmentModelRepository extends JpaRepository<EquipmentModel, UUID> {
}
