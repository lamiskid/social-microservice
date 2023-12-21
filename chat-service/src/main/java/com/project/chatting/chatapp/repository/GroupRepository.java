package com.project.chatting.chatapp.repository;


import com.project.chatting.chatapp.model.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupChat, Long> {

}
