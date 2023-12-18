package com.auth.payload;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AppUserDto {

    private Long id;
    public String email;
    public String username;

}
