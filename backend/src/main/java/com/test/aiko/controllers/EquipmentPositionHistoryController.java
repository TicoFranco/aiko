package com.test.aiko.controllers;

import com.test.aiko.dtos.request.EquipmentPositionHistoryDto;
import com.test.aiko.models.EquipmentPositionHistory;
import com.test.aiko.services.EquipmentPositionHistoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/equipment_position_history")
public class EquipmentPositionHistoryController {

    @Autowired
    private EquipmentPositionHistoryServices equipmentPositionHistoryServices;

    @GetMapping
    public ResponseEntity findAllEquipmentPositionHistory(){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentPositionHistoryServices.findAllEquipmentPositionHistory());
    }

    @GetMapping("/{id}")
    public ResponseEntity findEquipmentPositionHistoryById(@PathVariable UUID id){
        EquipmentPositionHistory equipmentPositionHistory = equipmentPositionHistoryServices.findEquipmentPositionHistoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(equipmentPositionHistory);
    }

    @PostMapping
    public ResponseEntity postEquipmentPositionHistory(@RequestBody EquipmentPositionHistoryDto equipmentPositionHistoryDto){
        EquipmentPositionHistory equipmentPositionHistory = equipmentPositionHistoryServices.postEquipmentPositionHistory(equipmentPositionHistoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentPositionHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity putEquipmentPositionHistory(@RequestBody EquipmentPositionHistoryDto equipmentPositionHistoryDto,@PathVariable UUID id){
        EquipmentPositionHistory equipmentPositionHistory = equipmentPositionHistoryServices.putEquipmentPositionHistory(id,equipmentPositionHistoryDto);
        return ResponseEntity.status(HttpStatus.OK).body(equipmentPositionHistory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEquipmentPositionHistory(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentPositionHistoryServices.deleteEquipmentPositionHistory(id));
    }
}
