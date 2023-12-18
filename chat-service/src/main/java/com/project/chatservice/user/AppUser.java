package com.project.chatservice.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
public class AppUser {

    private String username;
    private String email;
    //private Status status;
}
