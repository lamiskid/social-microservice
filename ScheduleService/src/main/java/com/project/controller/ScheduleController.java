package com.project.controller;



import com.project.model.Schedule;
import com.project.payload.ScheduleRequest;
import com.project.service.ScheduleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/schedule")
public class EventController {

    private final ScheduleService scheduleService;


    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createSchedule(@PathVariable("userId") Long userId,
                                                 @RequestBody ScheduleRequest scheduleRequest){

        scheduleService.createSchedule(userId, scheduleRequest);

        return ResponseEntity.ok("ScheduleService created successfully");

    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Schedule>> getUserSchedules(@PathVariable("userId") Long userId){

        return ResponseEntity.ok(scheduleService.getAUserSchedule(userId));

    }

    @DeleteMapping("/delete/{scheduleId}")
    private ResponseEntity<String> deleteSchedule(@PathVariable("eventId") Long eventId){

        scheduleService.deleteSchedule(eventId);

        return ResponseEntity.ok("Schedule delete successfully");

    }

    @PutMapping("/edit/{scheduleId}")
    private ResponseEntity<String>  editSchedule(@PathVariable Long scheduleId,@RequestBody ScheduleRequest scheduleRequest){

        scheduleService.editSchedule(scheduleId,scheduleRequest);

        return ResponseEntity.ok("Schedule updated successfully");

    }

}
