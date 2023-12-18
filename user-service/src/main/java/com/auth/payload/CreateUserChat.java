package com.auth.payload;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserChat {



    private String username;

    private String secret;

    private String email;

}
