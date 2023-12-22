package com.project.service;



import com.project.feignClient.UserFeignClient;
import com.project.model.AppUser;
import com.project.model.Schedule;
import com.project.payload.AppUserResponse;
import com.project.payload.CreateEventRequest;
import com.project.reposittory.AppUserRepository;
import com.project.reposittory.ScheduleRepository;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final AppUserRepository appUserRepository;

    private final UserFeignClient userFeignClient;

    public void createEvent(Long userId, CreateEventRequest eventRequest) {

        AppUserResponse appUser=getUserById(userId);

        Schedule schedule = Schedule
                .builder()
                .description(eventRequest.getDescription()).
                eventName(eventRequest.getEventName())
                .setAt(Instant.now())
                .appUser(appUserResponseToAppUserDto(appUser))
                .endAt(eventRequest.getExpiredDate()).build();
        scheduleRepository.save(schedule);

    }
    @Transactional
    public void deleteEvent(Long eventId) {
        Schedule schedule = scheduleRepository.findById(eventId).orElseThrow(() -> new RuntimeException("ScheduleService not found :id " + eventId));
        schedule.getAppUser().getSchedule();
        scheduleRepository.delete(schedule);

    }



    public List<Schedule> getAUserEvents(Long userId) {

        AppUser appUser = appUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("user with id " + userId + " not found"));

        return appUser.getSchedule();
    }
    public void editEvent(Long eventId, CreateEventRequest eventRequest) {
       Schedule schedule = scheduleRepository.findById(eventId).orElseThrow(() -> new RuntimeException("ScheduleService not found with id " + eventId));
        scheduleRepository.save(schedule);
    }

    private AppUser appUserResponseToAppUserDto(AppUserResponse response){

        AppUser appUser=new AppUser();
        appUser.setId(response.getId());
        appUser.setUserName(response.getUsername());

        return appUser;

    }


    private AppUserResponse getUserById(Long id) {
        return Objects.requireNonNull(userFeignClient.getUserById(id).getBody());

    }


}
