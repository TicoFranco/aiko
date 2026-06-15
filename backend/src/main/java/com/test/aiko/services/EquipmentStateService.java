package com.test.aiko.services;

import com.test.aiko.dtos.request.EquipmentStateDto;
import com.test.aiko.exceptions.ComponentNotFoundException;
import com.test.aiko.models.EquipmentState;
import com.test.aiko.repositories.EquipmentStateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentStateService {

    @Autowired
    private EquipmentStateRepository equipmentStateRepository;

    public List<EquipmentState> findAllEquipmentState(){
        return equipmentStateRepository.findAll();
    }

    public EquipmentState findEquipmentStateById(UUID id){
        Optional<EquipmentState> equipmentState = equipmentStateRepository.findById(id);
        return equipmentState.orElseThrow(() -> new ComponentNotFoundException("equipment state not found"));
    }

    public EquipmentState postEquipmentState(EquipmentStateDto equipmentStateDto){
        var equipmentState = new EquipmentState();
        BeanUtils.copyProperties(equipmentStateDto,equipmentState);
        equipmentStateRepository.save(equipmentState);
        return equipmentState;
    }

    public EquipmentState putEquipmentState(UUID id,EquipmentStateDto equipmentStateDto){
        EquipmentState findEquipmentState = findEquipmentStateById(id);
        var equipmentState = new EquipmentState();
        BeanUtils.copyProperties(equipmentStateDto,equipmentState);
        equipmentStateRepository.save(equipmentState);
        return equipmentState;
    }

    public String deleteEquipmentState(UUID id){
        Optional<EquipmentState> equipmentState = equipmentStateRepository.findById(id);
        if(equipmentState.isEmpty()){
            throw new ComponentNotFoundException("equipment state not found");
        }

        equipmentStateRepository.delete(equipmentState.get());
        return "deleted";
    }
}
