package com.project.controller;


import com.project.payload.AddUserRequest;
import com.project.payload.CreateMeetUpRequest;
import com.project.service.MeetUpService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meet")
public class MeetUpController {
    private final MeetUpService meetUpService;


    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createMeeting(@RequestBody CreateMeetUpRequest meetUp,
                                                @PathVariable("userId") Long userId){
        meetUpService.createMeetUp(meetUp,userId);
        return ResponseEntity.ok("Meeting created");
    }
    @PostMapping("/add-users/{meetingId}")
    public ResponseEntity<String> addUsersToMeetUp(@PathVariable("meetingId") Long meetingId,
                                                    @RequestBody List<String> usernames){
        meetUpService.addUsersToMeetUp(meetingId,usernames);
        return ResponseEntity.ok("user to meeting added successfully");
    }
    @GetMapping("/find/{userId}")
    public ResponseEntity<?> findMyMeetUps(@PathVariable("userId") Long userId){
        return ResponseEntity.ok( meetUpService.findMyMeetUps(userId));
    }
    @DeleteMapping("/meetup/{userId}/{meetingId}")
    public ResponseEntity<?> deleteMyMeetUps(@PathVariable("meetingId") Long meetingId,
                                             @PathVariable("userId") Long userId){
        meetUpService.deleteMeetUp(meetingId);
        return ResponseEntity.ok( "MeetUp deleted Successfully");
    }
}