package com.test.aiko.controllers;

import com.test.aiko.dtos.request.EquipmentModelStateHourlyEarningsDto;
import com.test.aiko.models.EquipmentModelStateHourlyEarnings;
import com.test.aiko.services.EquipmentModelStateHourlyEarningsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/equipment_model_state_hourly_earnings")
public class EquipmentModelStateHourlyEarningsController {

    @Autowired
    private EquipmentModelStateHourlyEarningsServices equipmentModelStateHourlyEarningsServices;

    @GetMapping
    public ResponseEntity findAllEquipmentModelStateHourlyEarnings(){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentModelStateHourlyEarningsServices.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findEquipmentModelStateHourlyEarningsById(@PathVariable UUID id){
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningsServices.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(equipmentModelStateHourlyEarnings);
    }

    @PostMapping
    public ResponseEntity postEquipmentModelStateHourlyEarnings(@RequestBody EquipmentModelStateHourlyEarningsDto equipmentModelStateHourlyEarningsDto){
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningsServices.postEquipmentModelStateHourlyEarnings(equipmentModelStateHourlyEarningsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentModelStateHourlyEarnings);
    }

    @PutMapping("/{id}")
    public ResponseEntity putEquipmentModelStateHourlyEarnings(@PathVariable UUID id,@RequestBody EquipmentModelStateHourlyEarningsDto equipmentModelStateHourlyEarningsDto){
        EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningsServices.putEquipmentModelStateHourlyEarnings(id,equipmentModelStateHourlyEarningsDto);
        return ResponseEntity.status(HttpStatus.OK).body(equipmentModelStateHourlyEarnings);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEquipmentModelStateHourlyEarnings(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentModelStateHourlyEarningsServices.deleteEquipmentModelStateHourlyEarnings(id));
    }

}
