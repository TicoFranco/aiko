package com.test.aiko.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "equipment_state")
public class EquipmentState {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @JsonIgnore
    @OneToMany(mappedBy = "equipmentState",cascade = CascadeType.ALL)
    private List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsList;

    @JsonIgnore
    @OneToMany(mappedBy = "equipmentState",cascade = CascadeType.ALL)
    private List<EquipmentStateHistory> equipmentStateHistoryList;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<EquipmentModelStateHourlyEarnings> getEquipmentModelStateHourlyEarningsList() {
        return equipmentModelStateHourlyEarningsList;
    }

    public void setEquipmentModelStateHourlyEarningsList(List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsList) {
        this.equipmentModelStateHourlyEarningsList = equipmentModelStateHourlyEarningsList;
    }

    public List<EquipmentStateHistory> getEquipmentStateHistoryList() {
        return equipmentStateHistoryList;
    }

    public void setEquipmentStateHistoryList(List<EquipmentStateHistory> equipmentStateHistoryList) {
        this.equipmentStateHistoryList = equipmentStateHistoryList;
    }
}
