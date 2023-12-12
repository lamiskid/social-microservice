package com.lamiskid.OAuth2project.controller;


import com.lamiskid.OAuth2project.config.AppUserDetails;
import com.lamiskid.OAuth2project.config.JwtUtils;
import com.lamiskid.OAuth2project.config.UserDetailServiceImpl;
import com.lamiskid.OAuth2project.payload.AuthRequest;
import com.lamiskid.OAuth2project.payload.LoginRequest;
import com.lamiskid.OAuth2project.payload.LoginResponse;
import com.lamiskid.OAuth2project.services.AuthService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
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
    private final JwtUtils jwtUtils;

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
