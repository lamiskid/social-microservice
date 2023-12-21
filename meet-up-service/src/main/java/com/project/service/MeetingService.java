package com.project.service;


import com.project.feignClient.UserFeignClient;
import com.project.model.AppUser;
import com.project.model.Meeting;
import com.project.payload.AppUserResponse;
import com.project.payload.CreateMeeting;
import com.project.repository.MeetingRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private final UserFeignClient userFeignClient;

    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    public Optional<Meeting> getMeetingById(Long id) {
        return meetingRepository.findById(id);
    }

    public Meeting createMeeting(CreateMeeting meetUpRequest) {

        Meeting meeting = Meeting.builder()
                                 .meetingName(meetUpRequest.getMeetingName())
                                 .city(meetUpRequest.getCity())
                                 .location(meetUpRequest.getLocation())
                                 .meetUpDate(meetUpRequest.getMeetUpDate())
                                 .organizer(appUserResponseToAppUserDto(getLogInUser()))
                                 .build();

        return meetingRepository.save(meeting);
    }

    public void deleteMeetingById(Long id) {

        meetingRepository.deleteById(id);
    }

    public void addAttendee(Long meetingId, Long userId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                                           .orElseThrow(() -> new RuntimeException("meeting with id " + meetingId + " not found"));

        AppUserResponse userResponse = getUserById(userId);

        AppUser appUser = appUserResponseToAppUserDto(userResponse);

        // Add the user to the meeting's attendees
        meeting.getAttendees().add(appUser);
        meetingRepository.save(meeting);
    }

    public void removeAttendee(Long meetingId, Long attendee) {
        Meeting meeting = meetingRepository.findById(meetingId)
                                           .orElseThrow(() -> new RuntimeException("meeting with id " +meetingId+" not found"));

        AppUserResponse userResponse = getUserById(attendee);

        if (meeting.getOrganizer().getUserId()==userResponse.getId()) {
            AppUser appUser = appUserResponseToAppUserDto(userResponse);

            // Remove the user from the meeting's attendees
            meeting.getAttendees().remove(appUser);
            meetingRepository.save(meeting);
        }
    }

    public List<Meeting> myMeetings() {

        AppUserResponse userResponse = getLogInUser();

         List<Meeting> meetings=   meetingRepository.findAll();

       List<Meeting> myMeetings= meetings.stream()
                                         .filter(meeting -> meeting.getOrganizer().getUserId()==userResponse.getId()).collect(Collectors.toList());


        return myMeetings;
    }


    private AppUserResponse getLogInUser() {
        return Objects.requireNonNull(userFeignClient.getUserLoginUser().getBody());
        //return Objects.requireNonNull(userFeignClient.getAllUserByUserNames(usernames).getBody()).getData();
    }
    private AppUserResponse getUserById(Long id) {
        return Objects.requireNonNull(userFeignClient.getUserById(id).getBody());

    }

    private AppUser appUserResponseToAppUserDto(AppUserResponse response){

        AppUser appUser=new AppUser();
        appUser.setUserId(response.getId());
        appUser.setUsername(response.getUsername());
        appUser.setEmail(response.email);

        return appUser;

    }



}
