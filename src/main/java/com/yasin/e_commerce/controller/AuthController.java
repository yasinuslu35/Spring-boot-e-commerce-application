package com.yasin.e_commerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yasin.e_commerce.business.concretes.AuthService;
import com.yasin.e_commerce.business.concretes.JwtService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.entities.concretes.User;
import com.yasin.e_commerce.entities.dto.requestes.RegisterUserDto;
import com.yasin.e_commerce.entities.dto.responses.LoginResponse;
import com.yasin.e_commerce.entities.dto.responses.LoginUserDto;
import com.yasin.e_commerce.entities.dto.responses.UserResponseDto;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private final JwtService jwtService;
    
    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<DataResult<UserResponseDto>> register(@RequestBody RegisterUserDto registerUserDto) 
    {
        ResponseEntity<DataResult<UserResponseDto>> registeredUser = 
        		authService.signup(registerUserDto);

        return registeredUser;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
