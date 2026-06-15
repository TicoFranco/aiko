package com.test.aiko.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.aiko.dtos.response.EquipmentResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "equipment_position_history")
public class EquipmentPositionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "equipment_id",nullable = false)
    @JsonIgnore
    private Equipment equipment;

    @Column(nullable = false)
    private LocalDateTime localDate;

    @Column(nullable = false)
    @Min(value = -90,message = "min value is -90")
    @Max(value = 90,message = "max value is 90")
    private double lat;

    @Column(nullable = false)
    @Min(value = -180,message = "min value is -180")
    @Max(value = 180,message = "max value is 180")
    private double lon;

    @Transient
    @JsonProperty("equipment")
    private EquipmentResponseDto equipmentResponseDto;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDateTime localDate) {
        this.localDate = localDate;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public EquipmentResponseDto getEquipmentResponseDto() {
        return equipmentResponseDto;
    }

    public void setEquipmentResponseDto(EquipmentResponseDto equipmentResponseDto) {
        this.equipmentResponseDto = equipmentResponseDto;
    }
}
