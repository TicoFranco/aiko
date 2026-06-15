package com.test.aiko.controllers;

import com.test.aiko.dtos.request.LoginDto;
import com.test.aiko.dtos.request.RegisterDto;
import com.test.aiko.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        return ResponseEntity.status(HttpStatus.OK).body(userServices.login(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userServices.register(registerDto));
    }
}
