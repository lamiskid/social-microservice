package com.project.controller;


import com.project.payload.AppUserResponse;
import com.project.service.CommunityService;
import java.net.http.HttpResponse.ResponseInfo;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/management")
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping("/getAllUsers")
    ResponseEntity<List<AppUserResponse>> getAllUser(){
       return ResponseEntity.ok(communityService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    ResponseEntity<AppUserResponse> getUserById(@PathVariable("id") Long id){
       return ResponseEntity.ok(communityService.getUserById(id));
    }



    @DeleteMapping("/remove-user/{id}")
    public ResponseEntity<String> removeUser(@PathVariable("id") Long id){
       return ResponseEntity.ok(communityService.removeUser(id));
    }


    @PostMapping("/enable-user/{userId}")
    public ResponseEntity<String> enableUser(@RequestParam("disable") Boolean enable,
                                            @PathVariable("userId") Long userId){
        return  ResponseEntity.ok(communityService.enableUser(enable,userId));
    }


    @DeleteMapping("/delete-meeting/{meetingId}")
    public ResponseEntity<Long> deleteMeetings(@PathVariable("meetingId") Long meetingId){
      return   ResponseEntity.ok(communityService.deleteMeeting(meetingId));

    }
    @GetMapping("/meeting/all")
    public ResponseEntity<?> getAllMeetings(){
       return ResponseEntity.ok(communityService.getAllMeetings());
    }








}
