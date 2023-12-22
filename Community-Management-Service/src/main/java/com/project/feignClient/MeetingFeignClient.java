package com.project.feignClient;


import com.project.payload.AppUserResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MEETING-SERVICE")
public interface MeetingFeignClient {


    @DeleteMapping("/api/v1/meet/admin/delete/{meetingId}")
    public ResponseEntity<Long> deleteMeetings(@PathVariable("meetingId") Long meetingId);
    @GetMapping("/api/v1/meet/all")
    public ResponseEntity<?> getAllMeetings();


}
