package com.project.chatting.chatapp.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupMessage {

    private Long senderId;
    private String content;



}
