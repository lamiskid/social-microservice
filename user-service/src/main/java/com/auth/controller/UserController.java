package com.auth.controller;


import com.auth.payload.AppUserDto;
import com.auth.services.AppUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private AppUserService appUserService;



    @GetMapping("/home")
    public String getHome(){
        return "Welcome";
    }


    @GetMapping("/login-user")
    public ResponseEntity<AppUserDto> getLoginUser(){

       return ResponseEntity.ok(appUserService.authenticatedUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDto> getUserById(@PathVariable("id") Long id){

        return ResponseEntity.ok(appUserService.findUserById(id));
    }



    @GetMapping("/getAllUsers")
    public ResponseEntity<List<AppUserDto>> getAllUsers(){

        return ResponseEntity.ok(appUserService.getAllUsers());

    }
    @GetMapping("/by-usernames")
    public ResponseEntity<List<AppUserDto>> getAllUsersByUserName(@RequestParam List<String> usernames){
        return ResponseEntity.ok(appUserService.getUsersByUserNames(usernames));
    }



}
