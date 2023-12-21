package com.project.chatting.chatapp.service;


import com.project.chatting.chatapp.model.AppUser;
import com.project.chatting.chatapp.model.GroupChat;
import com.project.chatting.chatapp.model.Messages;
import com.project.chatting.chatapp.payload.GroupMessage;
import com.project.chatting.chatapp.payload.PrivateMessage;
import com.project.chatting.chatapp.repository.AppUserRepository;
import com.project.chatting.chatapp.repository.MessagesRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {


    private final MessagesRepository messageRepository;

    private  final AppUserRepository appUserRepository;

    private final GroupService groupService;

    public List<Messages> getChatHistory(AppUser user1, AppUser user2) {
        return messageRepository.findBySenderOrReceiverOrderByTimestamp(user1, user2);
    }

    public List<Messages> getGroupChatHistory(GroupChat group) {
        return messageRepository.findByGroupOrderByTimestamp(group);
    }

    public Messages sendGroupMessage(GroupMessage groupMessage, Long groupId){

        GroupChat group = groupService.getGroupById(groupId);


        AppUser sender = appUserRepository
                .findById(groupMessage.getSenderId())
                        .orElseThrow(()-> new RuntimeException("sender not found  " +groupMessage.getSenderId()));
        Messages messages =new Messages();
        messages.setGroup(group);
        messages.setSender(sender);
        messages.setTimestamp(LocalDateTime.now());
        messages.setContent(groupMessage.getContent());

        // You can broadcast the message to the corresponding WebSocket topic here
        return messageRepository.save(messages);

    }

    public Messages sendPrivateMessage(PrivateMessage message){


        AppUser sender = appUserRepository.findById(message.getSenderId())
                                          .orElseThrow(() -> new RuntimeException("User not found "+message.getSenderId() ));
        AppUser receiver = appUserRepository.findById(message.getReceiverId())
                                            .orElseThrow(() -> new RuntimeException("User not found "+message.getSenderId() ));

        // Set the sender and receiver in the message object
        Messages messages=new Messages();
        messages.setSender(sender);
        messages.setReceiver(receiver);
        messages.setTimestamp(LocalDateTime.now());
        messages.setContent(message.getContent());

        // Save the message
        Messages savedMessage = messageRepository.save(messages);

        // You can broadcast the message to the corresponding WebSocket topic here

        return savedMessage;
    }

}
