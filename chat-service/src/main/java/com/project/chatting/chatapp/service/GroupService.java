package com.project.chatting.chatapp.service;

import com.project.chatting.chatapp.model.GroupChat;
import com.project.chatting.chatapp.repository.GroupRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public GroupChat createGroup(GroupChat group) {
        return groupRepository.save(group);
    }

    public GroupChat getGroupById(Long groupId){
        return groupRepository.getReferenceById(groupId);

    }

    public Long deleteGroup(String username,Long groupId){

     GroupChat groupChat=   groupRepository.findById(groupId)
                .orElseThrow(()-> new RuntimeException("group not found"));

     String creatorId= groupChat.getCreator().getUsername();

     if(creatorId.equalsIgnoreCase(username)){
         groupRepository.delete(groupChat);
         return groupChat.getId();
     }else {
         throw new RuntimeException("can not delete group : username not valid");
     }
    }
    public Long adminDeleteGroup(Long groupId){

        GroupChat groupChat=   groupRepository.findById(groupId)
                                              .orElseThrow(()-> new RuntimeException("group not found"));

        groupRepository.delete(groupChat);

        return groupChat.getId();

    }

    public List<GroupChat> getAllGroups() {
        return groupRepository.findAll();
    }
}
