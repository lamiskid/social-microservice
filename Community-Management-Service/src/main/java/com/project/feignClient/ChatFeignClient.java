package com.project.feignClient;

import com.project.payload.AppUserResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "CHAT-SERVICE")
public interface ChatFeignClient {



    @GetMapping("/api/v1/group-chat/admin/allGroup")
    public ResponseEntity<?> allGroupChat();
    @DeleteMapping("/api/v1/group-chat/admin/delete/{groupId}")
    public ResponseEntity<Long> deleteGroup( @PathVariable("groupId") Long groupId);

}