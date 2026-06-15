package com.test.aiko.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.aiko.dtos.response.EquipmentResponseDto;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "equipment_model")
public class EquipmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "equipmentModel",cascade = CascadeType.ALL)
    private List<Equipment> equipmentList;

    @JsonIgnore
    @OneToMany(mappedBy = "equipmentModel",cascade = CascadeType.ALL)
    private List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsList;

    @Transient
    @JsonProperty("equipments_list")
    private List<EquipmentResponseDto> equipmentResponseDtoList;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<EquipmentModelStateHourlyEarnings> getEquipmentModelStateHourlyEarningsList() {
        return equipmentModelStateHourlyEarningsList;
    }

    public void setEquipmentModelStateHourlyEarningsList(List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsList) {
        this.equipmentModelStateHourlyEarningsList = equipmentModelStateHourlyEarningsList;
    }

    public List<EquipmentResponseDto> getEquipmentResponseDtoList() {
        return equipmentResponseDtoList;
    }

    public void setEquipmentResponseDtoList(List<EquipmentResponseDto> equipmentResponseDtoList) {
        this.equipmentResponseDtoList = equipmentResponseDtoList;
    }
}
