package com.project.chatting.chatapp.controller;



import com.project.chatting.chatapp.model.AppUser;
import com.project.chatting.chatapp.model.GroupChat;
import com.project.chatting.chatapp.model.Messages;
import com.project.chatting.chatapp.payload.GroupMessage;
import com.project.chatting.chatapp.payload.PrivateMessage;
import com.project.chatting.chatapp.service.AppUserService;
import com.project.chatting.chatapp.service.GroupService;
import com.project.chatting.chatapp.service.MessageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private GroupService groupService;




    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Messages>> getGroupChatHistory(@PathVariable Long groupId) {
        GroupChat group = groupService.getGroupById(groupId);
        List<Messages> groupChatHistory = messageService.getGroupChatHistory(group);
        return ResponseEntity.ok(groupChatHistory);
    }

    @PostMapping("/send/group/{groupId}")
    public ResponseEntity<Messages> sendGroupMessage(@RequestBody GroupMessage groupMessage,
                                                     @PathVariable("groupId") Long groupId) {
        Messages savedMessage = messageService.sendGroupMessage(groupMessage,groupId);
        // You can broadcast the message to the corresponding WebSocket topic here
        return ResponseEntity.ok(savedMessage);
    }


    /////////////////////////////////////////////////////////////////////

    @GetMapping("/user/{user1}/{user2}")
    public ResponseEntity<List<Messages>> getPrivateChatHistory(@PathVariable("user1") String user1,
                                                                @PathVariable("user2") String user2) {
        AppUser sender = appUserService.getUserByUsername(user1);
        AppUser receiver = appUserService.getUserByUsername(user2);
        List<Messages> chatHistory = messageService.getChatHistory(sender, receiver);
        return ResponseEntity.ok(chatHistory);
    }

    @PostMapping("/send")
    public ResponseEntity<Messages> sendPrivateMessage(@RequestBody PrivateMessage message) {
        return ResponseEntity.ok(messageService.sendPrivateMessage(message));
    }




  /*  @GetMapping("/user/{user1}/{user2}")
    public ResponseEntity<List<Messages>> getChatHistory(@PathVariable String user1, @PathVariable String user2) {
        AppUser sender = appUserService.getUserByUsername(user1);
        AppUser receiver = appUserService.getUserByUsername(user2);
        List<Messages> chatHistory = messageService.getChatHistory(sender, receiver);
        return ResponseEntity.ok(chatHistory);
    }*/



}

