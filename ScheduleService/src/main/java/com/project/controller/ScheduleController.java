package com.project.controller;


import com.project.model.Schedule;
import com.project.payload.ScheduleRequest;
import com.project.service.ScheduleService;
import java.util.List;
import java.util.UUID;
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
public class ScheduleController {

    private final ScheduleService scheduleService;


    @PostMapping("/create")
    public ResponseEntity<String> createSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        scheduleService.createSchedule(scheduleRequest);
        return ResponseEntity.ok("Schedule created successfully");

    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Schedule>> getUserSchedules(@PathVariable("userId") Long userId) {

        return ResponseEntity.ok(scheduleService.getAUserSchedule(userId));
    }

    @DeleteMapping("/delete/{scheduleId}")
    private ResponseEntity<String> deleteSchedule(@PathVariable("scheduleId") UUID scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.ok("Schedule delete successfully");
    }

    @PutMapping("/edit/{scheduleId}")
    private ResponseEntity<String> editSchedule(@PathVariable UUID scheduleId, @RequestBody ScheduleRequest scheduleRequest) {

        scheduleService.editSchedule(scheduleId, scheduleRequest);

        return ResponseEntity.ok("Schedule updated successfully");

    }

}
