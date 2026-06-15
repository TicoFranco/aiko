package com.test.aiko.services;

import com.test.aiko.dtos.request.EquipmentPositionHistoryDto;
import com.test.aiko.dtos.response.EquipmentResponseDto;
import com.test.aiko.exceptions.ComponentNotFoundException;
import com.test.aiko.models.Equipment;
import com.test.aiko.models.EquipmentPositionHistory;
import com.test.aiko.repositories.EquipmentPositionHistoryRepository;
import com.test.aiko.repositories.EquipmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentPositionHistoryServices {

    @Autowired
    private EquipmentPositionHistoryRepository equipmentPositionHistoryRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    public void equipmentPositionHistoryResponse(EquipmentPositionHistory equipmentPositionHistory){
        equipmentPositionHistory.setEquipmentResponseDto(new EquipmentResponseDto(
                equipmentPositionHistory.getEquipment().getId(),
                equipmentPositionHistory.getEquipment().getName(),
                equipmentPositionHistory.getEquipment().getLicense()
        ));
    }

    public List<EquipmentPositionHistory> findAllEquipmentPositionHistory(){
        List<EquipmentPositionHistory> equipmentPositionHistoryList = equipmentPositionHistoryRepository.findAll();
        equipmentPositionHistoryList.forEach(this::equipmentPositionHistoryResponse);
        return equipmentPositionHistoryList;
    }

    public EquipmentPositionHistory findEquipmentPositionHistoryById(UUID id){
        Optional<EquipmentPositionHistory> equipmentPositionHistory = equipmentPositionHistoryRepository.findById(id);
        if(equipmentPositionHistory.isEmpty()){
            throw new ComponentNotFoundException("equipment position history not found");
        }
        equipmentPositionHistoryResponse(equipmentPositionHistory.get());
        return equipmentPositionHistory.get();
    }

    public EquipmentPositionHistory postEquipmentPositionHistory(EquipmentPositionHistoryDto equipmentPositionHistoryDto){
        Optional<Equipment> equipment = equipmentRepository.findById(equipmentPositionHistoryDto.equipment_id());
        if(equipment.isEmpty()){
            throw new ComponentNotFoundException("equipment not found");
        }

        var equipmentPositionHistory = new EquipmentPositionHistory();
        BeanUtils.copyProperties(equipmentPositionHistoryDto,equipmentPositionHistory);
        equipmentPositionHistory.setEquipment(equipment.get());
        equipmentPositionHistory.setLocalDate(LocalDateTime.now());
        equipmentPositionHistoryRepository.save(equipmentPositionHistory);
        equipmentPositionHistoryResponse(equipmentPositionHistory);
        return equipmentPositionHistory;
    }

    public EquipmentPositionHistory putEquipmentPositionHistory(UUID id,EquipmentPositionHistoryDto equipmentPositionHistoryDto){
        EquipmentPositionHistory findEquipmentPositionHistory = findEquipmentPositionHistoryById(id);

        Optional<Equipment> equipment = equipmentRepository.findById(equipmentPositionHistoryDto.equipment_id());
        if(equipment.isEmpty()){
            throw new ComponentNotFoundException("equipment not found");
        }

        var equipmentPositionHistory = new EquipmentPositionHistory();
        BeanUtils.copyProperties(equipmentPositionHistoryDto,equipmentPositionHistory);
        equipmentPositionHistory.setEquipment(equipment.get());
        equipmentPositionHistory.setLocalDate(LocalDateTime.now());
        equipmentPositionHistoryRepository.save(equipmentPositionHistory);
        equipmentPositionHistoryResponse(equipmentPositionHistory);
        return equipmentPositionHistory;
    }

    public String deleteEquipmentPositionHistory(UUID id){
        Optional<EquipmentPositionHistory> equipmentPositionHistory = equipmentPositionHistoryRepository.findById(id);
        if(equipmentPositionHistory.isEmpty()){
            throw new ComponentNotFoundException("equipment position history not found");
        }

        equipmentPositionHistoryRepository.delete(equipmentPositionHistory.get());
        return "deleted";
    }
}
