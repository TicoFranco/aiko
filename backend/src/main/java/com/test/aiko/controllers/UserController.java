package com.test.aiko.controllers;

import com.test.aiko.dtos.request.PostLicenseDto;
import com.test.aiko.dtos.request.PutUserDto;
import com.test.aiko.services.EquipmentServices;
import com.test.aiko.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private EquipmentServices equipmentServices;

    @GetMapping("/{email}")
    public ResponseEntity findUser(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentServices.getEquipmentsByUser(email));
    }

    @PutMapping
    public ResponseEntity putUser(@RequestBody PutUserDto putUserDto){
        return ResponseEntity.status(HttpStatus.OK).body(userServices.putUser(putUserDto));
    }

    @PostMapping("/license")
    public ResponseEntity postLicense(@RequestBody PostLicenseDto postLicenseDto){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentServices.postUserOwner(postLicenseDto));
    }

    @DeleteMapping("/license")
    public ResponseEntity removeEquipmentOwner(@RequestBody PostLicenseDto postLicenseDto){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentServices.removeEquipmentOwner(postLicenseDto));
    }

    @GetMapping("/profits/{email}")
    public ResponseEntity getProfitsTimelineByEquipment(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentServices.getProfitsTimelineByEquipment(email));
    }
}
