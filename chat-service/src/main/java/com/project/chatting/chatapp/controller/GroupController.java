package com.project.chatting.chatapp.controller;



import com.project.chatting.chatapp.model.GroupChat;
import com.project.chatting.chatapp.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<GroupChat> createGroup(@RequestBody GroupChat group) {
        GroupChat createdGroup = groupService.createGroup(group);
        return ResponseEntity.ok(createdGroup);
    }

    @DeleteMapping("/delete/{userId}/{groupId}")
    public ResponseEntity<Long> deleteGroup(@PathVariable("userId") Long userId,
                                            @PathVariable("groupId") Long groupId){

       Long deleted= groupService.deleteGroup(userId,groupId);
       return ResponseEntity.ok(deleted);
    }

}
