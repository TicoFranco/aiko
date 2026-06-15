package com.test.aiko.repositories;

import com.test.aiko.models.EquipmentModelStateHourlyEarnings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EquipmentModelStateHourlyEarningsRepository extends JpaRepository<EquipmentModelStateHourlyEarnings, UUID> {
    @Query("SELECT e FROM EquipmentModelStateHourlyEarnings e where e.equipmentModel.id = :id")
    List<EquipmentModelStateHourlyEarnings> getEquipmentModelStateHourlyEarningsByEquipmentModel(@Param("id") UUID id);
}
