package com.project.chatting.chatapp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AppUser {


   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Id
    private String username;
    private String email;


}
