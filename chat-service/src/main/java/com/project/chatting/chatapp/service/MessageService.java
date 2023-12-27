package com.project.chatting.chatapp.service;


import com.project.chatting.chatapp.feignClient.UserFeignClient;
import com.project.chatting.chatapp.model.AppUser;
import com.project.chatting.chatapp.model.GroupChat;
import com.project.chatting.chatapp.model.Messages;
import com.project.chatting.chatapp.payload.AppUserResponse;
import com.project.chatting.chatapp.payload.GroupMessage;
import com.project.chatting.chatapp.payload.PrivateMessage;
import com.project.chatting.chatapp.repository.AppUserRepository;
import com.project.chatting.chatapp.repository.MessagesRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {


    private final MessagesRepository messageRepository;

    private  final AppUserRepository appUserRepository;

    private final GroupService groupService;

    private final UserFeignClient userFeignClient;

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
        AppUserResponse userResponse=   getUser(message.getSenderId());
        AppUserResponse userResponse1=   getUser(message.getReceiverId());
        if(!appUserRepository.existsByUsername(userResponse.getUsername())){

            appUserRepository.save(resposenseToAppUser(userResponse));
        }
        if(!appUserRepository.existsByUsername(userResponse1.getUsername())){
            appUserRepository.save(resposenseToAppUser(userResponse1));
        }


        AppUser sender = appUserRepository.findByUsername(userResponse.getUsername())
                                          .orElseThrow(() -> new RuntimeException("User not found "+message.getSenderId() ));
        AppUser receiver = appUserRepository.findByUsername(userResponse1.getUsername())
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


    private AppUserResponse getUser(Long userId){
       return Objects.requireNonNull(userFeignClient.getUserById(userId).getBody());
    }

    private AppUser resposenseToAppUser(AppUserResponse appUserResponse){
        AppUser appUser =new AppUser();
        appUser.setEmail(appUserResponse.getEmail());
        appUser.setUsername(appUserResponse.getUsername());
        return appUser;
    }

}
