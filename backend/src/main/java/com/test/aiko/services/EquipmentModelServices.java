package com.test.aiko.services;

import com.test.aiko.dtos.request.EquipmentModelDto;
import com.test.aiko.dtos.response.EquipmentResponseDto;
import com.test.aiko.exceptions.ComponentNotFoundException;
import com.test.aiko.models.EquipmentModel;
import com.test.aiko.repositories.EquipmentModelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentModelServices {

    @Autowired
    private EquipmentModelRepository equipmentModelRepository;

    public void equipmentModelResponse(EquipmentModel equipmentModel){
        List<EquipmentResponseDto> equipmentResponseDtoList = new ArrayList<>();
        equipmentModel.getEquipmentList().forEach(equipment -> equipmentResponseDtoList.add(
                new EquipmentResponseDto(
                        equipment.getId(),
                        equipment.getName(),
                        equipment.getLicense()
                )
        ));
        equipmentModel.setEquipmentResponseDtoList(equipmentResponseDtoList);
    }

    public List<EquipmentModel> findAllEquipmentModels(){
        List<EquipmentModel> equipmentModelList = equipmentModelRepository.findAll();
        equipmentModelList.forEach(this::equipmentModelResponse);
        return equipmentModelList;
    }

    public EquipmentModel findEquipmentModelById(UUID id){
        Optional<EquipmentModel> equipmentModel = equipmentModelRepository.findById(id);
        if(equipmentModel.isEmpty()){
            throw new ComponentNotFoundException("equipment model not found");
        }
        equipmentModelResponse(equipmentModel.get());
        return equipmentModel.get();
    }

    public EquipmentModel postEquipmentModel(EquipmentModelDto equipmentModelDto){
        var equipmentModel = new EquipmentModel();
        BeanUtils.copyProperties(equipmentModelDto,equipmentModel);
        equipmentModelRepository.save(equipmentModel);
        equipmentModelResponse(equipmentModel);
        return equipmentModel;
    }

    public EquipmentModel putEquipmentModel(UUID id,EquipmentModelDto equipmentModelDto){
        EquipmentModel findEquipmentModel = findEquipmentModelById(id);
        var equipmentModel = new EquipmentModel();
        BeanUtils.copyProperties(equipmentModelDto,equipmentModel);
        equipmentModelRepository.save(equipmentModel);
        equipmentModelResponse(equipmentModel);
        return equipmentModel;
    }

    public String deleteEquipmentModel(UUID id){
        Optional<EquipmentModel> equipmentModel = equipmentModelRepository.findById(id);
        if(equipmentModel.isEmpty()){
            throw new ComponentNotFoundException("equipment model not found");
        }

        equipmentModelRepository.delete(equipmentModel.get());
        return "deleted";
    }
}
