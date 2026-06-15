package com.test.aiko.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.aiko.dtos.response.EquipmentModelResponseDto;
import com.test.aiko.dtos.response.EquipmentPositionHistoryResponseDto;
import com.test.aiko.dtos.response.EquipmentStateHistoryResponseDto;
import com.test.aiko.dtos.response.EquipmentOwnerResponseDto;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String license;

    @ManyToOne
    @JoinColumn(name = "equipment_model_id",nullable = false)
    @JsonIgnore
    private EquipmentModel equipmentModel;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JsonIgnore
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "equipment",cascade = CascadeType.ALL)
    private List<EquipmentStateHistory> equipmentStateHistoryList;

    @JsonIgnore
    @OneToMany(mappedBy = "equipment",cascade = CascadeType.ALL)
    private List<EquipmentPositionHistory> equipmentPositionHistoryList;

    @Transient
    @JsonProperty("Owner")
    private EquipmentOwnerResponseDto equipmentOwnerResponseDto;

    @Transient
    @JsonProperty("equipment_model")
    private EquipmentModelResponseDto equipmentModelResponseDto;

    @Transient
    @JsonProperty("equipment_position_history_list")
    private List<EquipmentPositionHistoryResponseDto> equipmentPositionHistoryResponseDtoList;

    @Transient
    @JsonProperty("equipment_state_history_list")
    private List<EquipmentStateHistoryResponseDto> equipmentStateHistoryResponseDtoList;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EquipmentModel getEquipmentModel() {
        return equipmentModel;
    }

    public List<EquipmentStateHistory> getEquipmentStateHistoryList() {
        return equipmentStateHistoryList;
    }

    public List<EquipmentPositionHistory> getEquipmentPositionHistoryList() {
        return equipmentPositionHistoryList;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEquipmentModel(EquipmentModel equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public void setEquipmentStateHistoryList(List<EquipmentStateHistory> equipmentStateHistoryList) {
        this.equipmentStateHistoryList = equipmentStateHistoryList;
    }

    public void setEquipmentPositionHistoryList(List<EquipmentPositionHistory> equipmentPositionHistoryList) {
        this.equipmentPositionHistoryList = equipmentPositionHistoryList;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<EquipmentPositionHistoryResponseDto> getEquipmentPositionHistoryResponseDtoList() {
        return equipmentPositionHistoryResponseDtoList;
    }

    public void setEquipmentPositionHistoryResponseDtoList(List<EquipmentPositionHistoryResponseDto> equipmentPositionHistoryResponseDtoList) {
        this.equipmentPositionHistoryResponseDtoList = equipmentPositionHistoryResponseDtoList;
    }

    public List<EquipmentStateHistoryResponseDto> getEquipmentStateHistoryResponseDtoList() {
        return equipmentStateHistoryResponseDtoList;
    }

    public void setEquipmentStateHistoryResponseDtoList(List<EquipmentStateHistoryResponseDto> equipmentStateHistoryResponseDtoList) {
        this.equipmentStateHistoryResponseDtoList = equipmentStateHistoryResponseDtoList;
    }

    public EquipmentModelResponseDto getEquipmentModelResponseDto() {
        return equipmentModelResponseDto;
    }

    public void setEquipmentModelResponseDto(EquipmentModelResponseDto equipmentModelResponseDto) {
        this.equipmentModelResponseDto = equipmentModelResponseDto;
    }

    public EquipmentOwnerResponseDto getEquipmentOwnerResponseDto() {
        return equipmentOwnerResponseDto;
    }

    public void setEquipmentOwnerResponseDto(EquipmentOwnerResponseDto equipmentOwnerResponseDto) {
        this.equipmentOwnerResponseDto = equipmentOwnerResponseDto;
    }
}
