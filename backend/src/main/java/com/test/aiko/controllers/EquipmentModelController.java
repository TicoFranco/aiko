package com.test.aiko.controllers;

import com.test.aiko.dtos.request.EquipmentModelDto;
import com.test.aiko.models.EquipmentModel;
import com.test.aiko.services.EquipmentModelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/equipment_model")
public class EquipmentModelController {

    @Autowired
    private EquipmentModelServices equipmentModelServices;

    @GetMapping
    public ResponseEntity findAllEquipmentModels(){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentModelServices.findAllEquipmentModels());
    }

    @GetMapping("/{id}")
    public ResponseEntity findEquipmentModelById(@PathVariable UUID id){
        EquipmentModel equipmentModel = equipmentModelServices.findEquipmentModelById(id);
        return ResponseEntity.status(HttpStatus.OK).body(equipmentModel);
    }

    @PostMapping
    public ResponseEntity postEquipmentModel(@RequestBody EquipmentModelDto equipmentModelDto){
        EquipmentModel equipmentModel = equipmentModelServices.postEquipmentModel(equipmentModelDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity putEquipmentModel(@PathVariable UUID id,@RequestBody EquipmentModelDto equipmentModelDto){
        EquipmentModel equipmentModel = equipmentModelServices.putEquipmentModel(id,equipmentModelDto);
        return ResponseEntity.status(HttpStatus.OK).body(equipmentModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEquipmentModel(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentModelServices.deleteEquipmentModel(id));
    }
}
