package com.project.service;



import com.project.feignClient.UserFeignClient;

import com.project.model.AppUser;
import com.project.model.Schedule;
import com.project.payload.AppUserResponse;
import com.project.payload.ScheduleRequest;
import com.project.reposittory.AppUserRepository;
import com.project.reposittory.ScheduleRepository;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final AppUserRepository appUserRepository;

    private final UserFeignClient userFeignClient;


    @Transactional
    public void createSchedule(ScheduleRequest scheduleRequest) {
        AppUserResponse appUserResponse=getUserById(scheduleRequest.getUserId());

        Schedule schedule = Schedule
                .builder()
                .appUserId(appUserResponse.getId())
                .description(scheduleRequest.getDescription())
                .title(scheduleRequest.getTitle())
                .setAt(Instant.now())
                .endAt(scheduleRequest.getEndAt()).build();

        scheduleRepository.save(schedule);

    }
    @Transactional
    public void deleteSchedule(UUID scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("Schedule not found :id " + scheduleId));

        scheduleRepository.delete(schedule);

    }



    public List<Schedule> getAUserSchedule(Long userId) {

     return scheduleRepository.findByAppUserId(userId);

    }

    @Transactional
    public void editSchedule(UUID scheduleId, ScheduleRequest scheduleRequest) {
       Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("Schedule not found with id " + scheduleId));

       schedule.setDescription(scheduleRequest.getDescription());
       schedule.setEndAt(scheduleRequest.getEndAt());
        schedule.setTitle(scheduleRequest.getTitle());
       // scheduleRepository.save(schedule);
    }

    private AppUser appUserResponseToAppUserDto(AppUserResponse response){

        AppUser appUser=new AppUser();
       // appUser.setId(response.getId());
        appUser.setUsername(response.getUsername());
        appUser.setEmail(response.getEmail());

        return appUser;

    }

    private AppUserResponse getUserById(Long id) {
        return Objects.requireNonNull(userFeignClient.getUserById(id).getBody());

    }


}
