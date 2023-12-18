package com.project.chatservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChaController {

    @GetMapping
    public ResponseEntity<?> getMyAccount(){

       return ResponseEntity.ok("");


    }

}
