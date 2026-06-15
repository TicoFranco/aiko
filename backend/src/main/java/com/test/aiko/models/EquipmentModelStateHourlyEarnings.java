package com.test.aiko.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.aiko.dtos.response.EquipmentModelResponseDto;
import com.test.aiko.dtos.response.EquipmentStateResponseDto;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "equipment_model_state_hourly_earnings")
public class EquipmentModelStateHourlyEarnings {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "equipment_model_id",nullable = false)
    @JsonIgnore
    private EquipmentModel equipmentModel;

    @ManyToOne
    @JoinColumn(name = "equipment_state_id",nullable = false)
    @JsonIgnore
    private EquipmentState equipmentState;

    @Column(nullable = false)
    private int value;

    @Transient
    @JsonProperty("equipment_model")
    private EquipmentModelResponseDto equipmentModelResponseDto;

    @Transient
    @JsonProperty("equipment_state")
    private EquipmentStateResponseDto equipmentStateResponseDto;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EquipmentModel getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(EquipmentModel equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public EquipmentState getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(EquipmentState equipmentState) {
        this.equipmentState = equipmentState;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public EquipmentModelResponseDto getEquipmentModelResponseDto() {
        return equipmentModelResponseDto;
    }

    public void setEquipmentModelResponseDto(EquipmentModelResponseDto equipmentModelResponseDto) {
        this.equipmentModelResponseDto = equipmentModelResponseDto;
    }

    public EquipmentStateResponseDto getEquipmentStateResponseDto() {
        return equipmentStateResponseDto;
    }

    public void setEquipmentStateResponseDto(EquipmentStateResponseDto equipmentStateResponseDto) {
        this.equipmentStateResponseDto = equipmentStateResponseDto;
    }
}
