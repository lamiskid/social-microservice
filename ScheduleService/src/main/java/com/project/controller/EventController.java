package com.project.controller;



import com.project.model.Schedule;
import com.project.payload.CreateEventRequest;
import com.project.service.ScheduleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/event")
public class EventController {

    private final ScheduleService scheduleService;


    @PostMapping("/create/{userId}")
    private ResponseEntity<String> createSchedule(@PathVariable("userId") Long userId, CreateEventRequest createEventRequest){

        scheduleService.createEvent(userId,createEventRequest);

        return ResponseEntity.ok("ScheduleService created successfully");

    }


    @GetMapping("/all)")
    private ResponseEntity<List<Schedule>> getUserSchedules(@PathVariable("userId") Long userId){

        return ResponseEntity.ok(scheduleService.getAUserEvents(userId));

    }

    @DeleteMapping("/delete/{eventId}")
    private ResponseEntity<String> deleteSchedule(@PathVariable("eventId") Long eventId){

        scheduleService.deleteEvent(eventId);

        return ResponseEntity.ok("ScheduleService delete successfully");

    }

}
