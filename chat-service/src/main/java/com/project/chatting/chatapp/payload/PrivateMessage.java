package com.project.chatting.chatapp.payload;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrivateMessage {

    private Long senderId;

    private Long receiverId;

    private String content;


}
