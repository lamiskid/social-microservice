package com.project.chatting.chatapp.repository;



import com.project.chatting.chatapp.model.AppUser;
import com.project.chatting.chatapp.model.GroupChat;
import com.project.chatting.chatapp.model.Messages;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Long> {
    List<Messages> findBySenderOrReceiverOrderByTimestamp(AppUser sender, AppUser receiver);
    List<Messages> findByGroupOrderByTimestamp(GroupChat group);
}
