package com.test.aiko.controllers;

import com.test.aiko.dtos.request.EquipmentDto;
import com.test.aiko.models.Equipment;
import com.test.aiko.services.EquipmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentServices equipmentServices;

    @GetMapping
    public ResponseEntity findAllEquipments(){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentServices.findAllEquipments());
    }

    @GetMapping("/{id}")
    public ResponseEntity findEquipmentById(@PathVariable UUID id){
        Equipment equipment = equipmentServices.findEquipmentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(equipment);
    }

    @PostMapping
    public ResponseEntity postEquipment(@RequestBody EquipmentDto equipmentDto){
        Equipment equipment = equipmentServices.postEquipment(equipmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity putEquipment(@RequestBody EquipmentDto equipmentDto,@PathVariable UUID id){
        Equipment equipment = equipmentServices.putEquipment(id,equipmentDto);
        return ResponseEntity.status(HttpStatus.OK).body(equipment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEquipment(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentServices.deleteEquipment(id));
    }
}
