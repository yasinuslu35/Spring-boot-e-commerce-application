package com.yasin.e_commerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yasin.e_commerce.business.concretes.AuthService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.entities.dto.requestes.RegisterUserDto;
import com.yasin.e_commerce.entities.dto.responses.LoginResponse;
import com.yasin.e_commerce.entities.dto.responses.LoginUserDto;
import com.yasin.e_commerce.entities.dto.responses.UserResponseDto;

import jakarta.validation.Valid;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    
    private final AuthService authService;
    
    public AuthController(
    		AuthService authService

    		) {
        this.authService = authService;

    }

    @PostMapping("/signup")
    public ResponseEntity<DataResult<UserResponseDto>> register(@Valid @RequestBody RegisterUserDto registerUserDto) 
    {
        return authService.signup(registerUserDto);
    }

    @PostMapping("/login")
    public ResponseEntity<DataResult<LoginResponse>> authenticate(@Valid @RequestBody LoginUserDto loginUserDto) {

        return authService.authenticate(loginUserDto);
    }
    
}
