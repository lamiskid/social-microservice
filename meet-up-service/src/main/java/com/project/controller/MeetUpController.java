package com.project.controller;


import com.project.model.Meeting;
import com.project.payload.CreateMeeting;
import com.project.service.MeetingService;
import jakarta.ws.rs.Path;
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
    public ResponseEntity<String> createMeeting(@RequestBody CreateMeeting meetUp,@PathVariable("userId") Long userId){
        meetingService.createMeeting(meetUp,userId);
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

    @GetMapping("/my-meetings/{userId}")
    public ResponseEntity<List<Meeting>> getMyMeetings(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(meetingService.myMeetings(userId));
    }
    @DeleteMapping("/delete/{userId}/{meetingId}")
    public ResponseEntity<Long> deleteMeetings(@PathVariable("meetingId") Long meetingId,
                                               @PathVariable("userId") Long userId){
        return ResponseEntity.ok(meetingService.deleteMeeting(meetingId,userId));
    }
    @DeleteMapping("/admin/delete/{meetingId}")
    public ResponseEntity<Long> deleteMeetings(@PathVariable("meetingId") Long meetingId){
        return ResponseEntity.ok(meetingService.deleteMeetingByAdmin(meetingId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Meeting>> getAllMeetings(){
        return ResponseEntity.ok(meetingService.getAllMeetings());
    }
    @DeleteMapping("/meetup/{userId}/{meetingId}/{attendeeId}")
    public ResponseEntity<?> removeAttendee(@PathVariable("meetingId") Long meetingId,
                                            @PathVariable("userId") Long userId,
                                             @PathVariable("attendeeId") Long attendeeId){
        meetingService.removeAttendee(meetingId,userId,attendeeId);
        return ResponseEntity.ok( "removed Successfully");
    }
}