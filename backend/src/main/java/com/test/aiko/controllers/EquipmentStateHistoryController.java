package com.test.aiko.controllers;

import com.test.aiko.dtos.request.EquipmentStateHistoryDto;
import com.test.aiko.models.EquipmentStateHistory;
import com.test.aiko.services.EquipmentStateHistoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/equipment_state_history")
public class EquipmentStateHistoryController {

    @Autowired
    private EquipmentStateHistoryServices equipmentStateHistoryServices;

    @GetMapping
    public ResponseEntity findAllEquipmentStateHistorys(){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentStateHistoryServices.findAllEquipmentStateHistory());
    }

    @GetMapping("/{id}")
    public ResponseEntity findEquipmentStateHistoryById(@PathVariable UUID id){
        EquipmentStateHistory equipmentStateHistory = equipmentStateHistoryServices.findEquipmentStateHistoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(equipmentStateHistory);
    }

    @PostMapping
    public ResponseEntity postEquipmentStateHistory(@RequestBody EquipmentStateHistoryDto equipmentStateHistoryDto){
        EquipmentStateHistory equipmentStateHistory = equipmentStateHistoryServices.postEquipmentStateHistory(equipmentStateHistoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentStateHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity putEquipmentStateHistory(@PathVariable UUID id,@RequestBody EquipmentStateHistoryDto equipmentStateHistoryDto){
        EquipmentStateHistory equipmentStateHistory = equipmentStateHistoryServices.putEquipmentStateHistory(id,equipmentStateHistoryDto);
        return ResponseEntity.status(HttpStatus.OK).body(equipmentStateHistory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEquipmentStateHistory(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentStateHistoryServices.deleteEquipmentStateHistory(id));
    }
}
