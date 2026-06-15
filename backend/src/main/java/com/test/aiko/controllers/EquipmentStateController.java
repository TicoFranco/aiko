package com.test.aiko.controllers;

import com.test.aiko.dtos.request.EquipmentStateDto;
import com.test.aiko.models.EquipmentState;
import com.test.aiko.services.EquipmentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/equipment_state")
public class EquipmentStateController{

    @Autowired
    private EquipmentStateService equipmentStateService;

    @GetMapping
    public ResponseEntity findAllEquipmentState(){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentStateService.findAllEquipmentState());
    }

    @GetMapping("/{id}")
    public ResponseEntity findEquipmentStateById(@PathVariable UUID id){
        EquipmentState equipmentState = equipmentStateService.findEquipmentStateById(id);
        return ResponseEntity.status(HttpStatus.OK).body(equipmentState);
    }

    @PostMapping
    public ResponseEntity postEquipmentState(@RequestBody EquipmentStateDto equipmentStateDto){
        EquipmentState equipmentState = equipmentStateService.postEquipmentState(equipmentStateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentState);
    }

    @PutMapping("/{id}")
    public ResponseEntity putEquipmentState(@PathVariable UUID id,@RequestBody EquipmentStateDto equipmentStateDto){
        EquipmentState equipmentState = equipmentStateService.putEquipmentState(id,equipmentStateDto);
        return ResponseEntity.status(HttpStatus.OK).body(equipmentState);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEquipmentState(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentStateService.deleteEquipmentState(id));
    }

}
