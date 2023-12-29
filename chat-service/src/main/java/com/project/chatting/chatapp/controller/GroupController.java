package com.project.chatting.chatapp.controller;



import com.project.chatting.chatapp.model.GroupChat;
import com.project.chatting.chatapp.service.GroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/group-chat")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<GroupChat> createGroup(@RequestBody GroupChat group) {
        GroupChat createdGroup = groupService.createGroup(group);
        return ResponseEntity.ok(createdGroup);
    }

    @DeleteMapping("/delete/{username}/{groupId}")
    public ResponseEntity<Long> deleteGroupByCreator(@PathVariable("username") String username,
                                            @PathVariable("groupId") Long groupId){

       Long deleted= groupService.deleteGroup(username,groupId);
       return ResponseEntity.ok(deleted);
    }

    @GetMapping("/admin/allGroup")
    public ResponseEntity<List<GroupChat>> allGroupChat(){
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @DeleteMapping("/admin/delete/{groupId}")
    public ResponseEntity<Long> deleteGroup( @PathVariable("groupId") Long groupId){
        return ResponseEntity.ok(groupService.adminDeleteGroup(groupId));
    }


}
