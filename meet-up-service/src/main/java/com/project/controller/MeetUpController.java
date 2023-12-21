package com.project.controller;


import com.project.model.Meeting;
import com.project.payload.CreateMeeting;
import com.project.service.MeetingService;
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
    private final MeetingService meetingService;


    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createMeeting(@RequestBody CreateMeeting meetUp){
        meetingService.createMeeting(meetUp);
        return ResponseEntity.ok("Meeting created");
    }
    @PostMapping("/add-users/{meetingId}/{attendeeId}")
    public ResponseEntity<String> addUsersToMeetUp(@PathVariable("meetingId") Long meetingId,
                                                    @PathVariable("attendeeId") Long attendeeId){
        //TODO
        meetingService.addAttendee(meetingId,attendeeId);
        return ResponseEntity.ok("user to meeting added successfully");
    }
    /*@GetMapping("/find/{userId}")
    public ResponseEntity<?> findMyMeetings(@PathVariable("userId") String username){
        return ResponseEntity.ok( meetUpService.findMyMeetUps(username));
    }*/

    @GetMapping("/my-meetings")
    public ResponseEntity<List<Meeting>> getCreatedMeetings(){
        return ResponseEntity.ok(meetingService.myMeetings());
    }

    @GetMapping("/")
    public ResponseEntity<List<Meeting>> getAllMeetings(){
        return ResponseEntity.ok(meetingService.getAllMeetings());
    }
    @DeleteMapping("/meetup/{meetingId}/{attendeeId}")
    public ResponseEntity<?> removeAttendee(@PathVariable("meetingId") Long meetingId,
                                             @PathVariable("attendeeId") Long attendeeId){
        meetingService.removeAttendee(meetingId,attendeeId);
        return ResponseEntity.ok( "MeetUp deleted Successfully");
    }
}