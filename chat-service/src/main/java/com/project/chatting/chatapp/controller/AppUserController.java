package com.project.chatting.chatapp.controller;


import com.project.chatting.chatapp.model.AppUser;
import com.project.chatting.chatapp.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AppUserController {

    @Autowired
    private AppUserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> getUserByUsername(@PathVariable String username) {
        AppUser user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

}
