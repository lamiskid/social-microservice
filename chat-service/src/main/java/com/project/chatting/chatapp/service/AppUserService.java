package com.project.chatting.chatapp.service;


import com.project.chatting.chatapp.model.AppUser;
import com.project.chatting.chatapp.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppUserService {

    @Autowired
    private AppUserRepository userRepository;

    public AppUser getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                             .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
