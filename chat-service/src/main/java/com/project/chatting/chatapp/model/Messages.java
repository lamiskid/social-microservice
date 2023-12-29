package com.project.chatting.chatapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private AppUser sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private AppUser receiver;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupChat group;
    private String content;
    private LocalDateTime timestamp;

}
