package com.test.aiko.services;

import com.test.aiko.config.TokenConfig;
import com.test.aiko.dtos.request.LoginDto;
import com.test.aiko.dtos.request.PutUserDto;
import com.test.aiko.dtos.request.RegisterDto;
import com.test.aiko.dtos.response.LoginResponseDto;
import com.test.aiko.dtos.response.RegisterResponseDto;
import com.test.aiko.exceptions.UserNotFoundException;
import com.test.aiko.models.User;
import com.test.aiko.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenConfig tokenConfig;

    public LoginResponseDto login(LoginDto loginDto){
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(loginDto.email(),loginDto.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);
        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);

        return new LoginResponseDto(token);
    }

    public RegisterResponseDto register(RegisterDto registerDto){
        var user = new User();
        BeanUtils.copyProperties(registerDto,user);
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        userRepository.save(user);
        return new RegisterResponseDto(user.getUsername(),user.getEmail());
    }

    public User findUser(String email){
        Optional<User> user = userRepository.findUser(email);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }

        return user.get();
    }

    public RegisterResponseDto putUser(PutUserDto putUserDto){
        User user = findUser(putUserDto.email());
        if(putUserDto.password() != null){
            if(!putUserDto.password().isBlank()){
                user.setPassword(passwordEncoder.encode(putUserDto.password()));
            }
        }
        if(putUserDto.username() != null){
            if(!putUserDto.username().isBlank()){
                user.setUsername(putUserDto.username());
            }
        }
        userRepository.save(user);
        return new RegisterResponseDto(user.getUsername(),user.getEmail());
    }
}
