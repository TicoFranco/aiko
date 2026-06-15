package com.test.aiko.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.aiko.dtos.response.EquipmentResponseDto;
import com.test.aiko.dtos.response.EquipmentStateResponseDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "equipment_state_history")
public class EquipmentStateHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "equipment_id",nullable = false)
    @JsonIgnore
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "equipment_state_id",nullable = false)
    @JsonIgnore
    private EquipmentState equipmentState;

    @Column(nullable = false)
    private LocalDateTime localDate;

    @Transient
    @JsonProperty("equipment")
    private EquipmentResponseDto equipmentResponseDto;

    @Transient
    @JsonProperty("equipment_state")
    private EquipmentStateResponseDto equipmentStateResponseDto;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int profity;

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

    public EquipmentState getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(EquipmentState equipmentState) {
        this.equipmentState = equipmentState;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDateTime localDate) {
        this.localDate = localDate;
    }

    public EquipmentResponseDto getEquipmentResponseDto() {
        return equipmentResponseDto;
    }

    public void setEquipmentResponseDto(EquipmentResponseDto equipmentResponseDto) {
        this.equipmentResponseDto = equipmentResponseDto;
    }

    public EquipmentStateResponseDto getEquipmentStateResponseDto() {
        return equipmentStateResponseDto;
    }

    public void setEquipmentStateResponseDto(EquipmentStateResponseDto equipmentStateResponseDto) {
        this.equipmentStateResponseDto = equipmentStateResponseDto;
    }

    public int getProfity() {
        return profity;
    }

    public void setProfity(int profity) {
        this.profity = profity;
    }
}
