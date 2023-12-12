package com.lamiskid.OAuth2project.controller;


import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/home")
    public String getHome(){
        return "Welcome";
    }

    @GetMapping("/hom")
    public String getHom( Authentication authenticatedPrincipal){
        authenticatedPrincipal.getName();
        return  authenticatedPrincipal.getName();
    }


}
