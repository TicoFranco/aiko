package com.test.aiko.services;

import com.test.aiko.dtos.request.EquipmentStateHistoryDto;
import com.test.aiko.dtos.response.EquipmentResponseDto;
import com.test.aiko.dtos.response.EquipmentStateResponseDto;
import com.test.aiko.exceptions.ComponentNotFoundException;
import com.test.aiko.models.Equipment;
import com.test.aiko.models.EquipmentState;
import com.test.aiko.models.EquipmentStateHistory;
import com.test.aiko.repositories.EquipmentRepository;
import com.test.aiko.repositories.EquipmentStateHistoryRepository;
import com.test.aiko.repositories.EquipmentStateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentStateHistoryServices {

    @Autowired
    private EquipmentStateHistoryRepository equipmentStateHistoryRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentStateRepository equipmentStateRepository;

    public void equipmentStateHistoryResponse(EquipmentStateHistory equipmentStateHistory){
        equipmentStateHistory.setEquipmentStateResponseDto(new EquipmentStateResponseDto(
                equipmentStateHistory.getEquipmentState().getId(),
                equipmentStateHistory.getEquipmentState().getName(),
                equipmentStateHistory.getEquipmentState().getColor()
        ));
        equipmentStateHistory.setEquipmentResponseDto(new EquipmentResponseDto(
                equipmentStateHistory.getEquipment().getId(),
                equipmentStateHistory.getEquipment().getName(),
                equipmentStateHistory.getEquipment().getLicense()
        ));
    }

    public List<EquipmentStateHistory> findAllEquipmentStateHistory(){
        List<EquipmentStateHistory> equipmentStateHistoryList = equipmentStateHistoryRepository.findAll();
        equipmentStateHistoryList.forEach(this::equipmentStateHistoryResponse);
        return equipmentStateHistoryList;
    }

    public EquipmentStateHistory findEquipmentStateHistoryById(UUID id){
        Optional<EquipmentStateHistory> equipmentStateHistory = equipmentStateHistoryRepository.findById(id);
        if(equipmentStateHistory.isEmpty()){
            throw new ComponentNotFoundException("equipment state history not found");
        }
        equipmentStateHistoryResponse(equipmentStateHistory.get());
        return equipmentStateHistory.get();
    }

    public EquipmentStateHistory postEquipmentStateHistory(EquipmentStateHistoryDto equipmentStateHistoryDto){
        Optional<Equipment> equipment = equipmentRepository.findById(equipmentStateHistoryDto.equipment_id());
        if(equipment.isEmpty()){
            throw new ComponentNotFoundException("equipment not found");
        }
        Optional<EquipmentState> equipmentState = equipmentStateRepository.findById(equipmentStateHistoryDto.equipment_state_id());
        if(equipmentState.isEmpty()){
            throw new ComponentNotFoundException("equipment state not found");
        }

        var equipmentStateHistory = new EquipmentStateHistory();
        BeanUtils.copyProperties(equipmentStateHistoryDto,equipmentStateHistory);
        equipmentStateHistory.setEquipment(equipment.get());
        equipmentStateHistory.setEquipmentState(equipmentState.get());
        equipmentStateHistory.setLocalDate(LocalDateTime.now());
        equipmentStateHistoryRepository.save(equipmentStateHistory);
        equipmentStateHistoryResponse(equipmentStateHistory);
        return equipmentStateHistory;
    }

    public EquipmentStateHistory putEquipmentStateHistory(UUID id, EquipmentStateHistoryDto equipmentStateHistoryDto){
        EquipmentStateHistory findEquipmentStateHistory = findEquipmentStateHistoryById(id);

        Optional<Equipment> equipment = equipmentRepository.findById(equipmentStateHistoryDto.equipment_id());
        if(equipment.isEmpty()){
            throw new ComponentNotFoundException("equipment not found");
        }
        Optional<EquipmentState> equipmentState = equipmentStateRepository.findById(equipmentStateHistoryDto.equipment_state_id());
        if(equipmentState.isEmpty()){
            throw new ComponentNotFoundException("equipment state not found");
        }

        var equipmentStateHistory = new EquipmentStateHistory();
        BeanUtils.copyProperties(equipmentStateHistoryDto,equipmentStateHistory);
        equipmentStateHistory.setEquipment(equipment.get());
        equipmentStateHistory.setEquipmentState(equipmentState.get());
        equipmentStateHistory.setLocalDate(LocalDateTime.now());
        equipmentStateHistoryRepository.save(equipmentStateHistory);
        equipmentStateHistoryResponse(equipmentStateHistory);
        return equipmentStateHistory;
    }

    public String deleteEquipmentStateHistory(UUID id){
       Optional<EquipmentStateHistory> equipmentStateHistory = equipmentStateHistoryRepository.findById(id);
       if(equipmentStateHistory.isEmpty()){
           throw new ComponentNotFoundException("equipment state history not found");
       }

       equipmentStateHistoryRepository.delete(equipmentStateHistory.get());
       return "deleted";
    }

}
