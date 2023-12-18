package com.auth.controller;


import com.auth.config.JwtUtils;
import com.auth.payload.AuthRequest;
import com.auth.payload.LoginRequest;
import com.auth.payload.LoginResponse;
import com.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    private final AuthenticationManager authenticationManager;


    @PostMapping("/sign-up")
    public void createUser(@RequestBody AuthRequest authRequest){

        authService.createUser(authRequest);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateAndGetToken(@RequestBody LoginRequest authRequest) {

       return ResponseEntity.ok(authService.signin(authRequest));
    }


}
