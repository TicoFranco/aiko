package com.test.aiko.repositories;

import com.test.aiko.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {
    @Query("SELECT e FROM Equipment e where e.license = :license")
    Optional<Equipment> findEquipmentByLicense(@Param("license") String license);
}
