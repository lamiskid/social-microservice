package com.project.service;


import com.project.feignClient.UserFeignClient;
import com.project.model.Attendee;
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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Meeting createMeeting(CreateMeeting meetUpRequest,Long userId) {

      AppUserResponse response=   getUserById(userId);



        Meeting meeting = Meeting.builder()
                                 .meetingName(meetUpRequest.getMeetingName())
                                 .city(meetUpRequest.getCity())
                                 .location(meetUpRequest.getLocation())
                                 .meetUpDate(meetUpRequest.getMeetUpDate())
                                    .organizerId(response.getId())
                                // .organizer(appUserResponseToAppUserDto(getLogInUser()))
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

        Attendee appUser = appUserResponseToAppUserDto(userResponse);

        // Add the user to the meeting's attendees
        meeting.getAttendees().add(appUser);
        meetingRepository.save(meeting);
    }

    @Transactional
    public void removeAttendee(Long meetingId,Long userId, Long attendee) {
        Meeting meeting = meetingRepository.findById(meetingId)
                                           .orElseThrow(() -> new RuntimeException("meeting with id " +meetingId+" not found"));

        AppUserResponse userResponse = getUserById(attendee);

        if (meeting.getOrganizerId()==userId) {
            Attendee appUserAttendee = appUserResponseToAppUserDto(userResponse);

            // Remove the user from the meeting's attendees
            meeting.getAttendees().remove(appUserAttendee);
            meetingRepository.save(meeting);
        }else {
            throw new RuntimeException("You do not have permission to remove this user ");
        }
    }

    public List<Meeting> myMeetings(Long userId) {

        AppUserResponse userResponse = getUserById(userId);

         List<Meeting> meetings=   meetingRepository.findAll();

       List<Meeting> myMeetings= meetings.stream()
                                         .filter(meeting -> meeting.getOrganizerId()==userResponse.getId()).collect(Collectors.toList());


        return myMeetings;
    }


    @Transactional
    public Long deleteMeeting(Long meetingId){

        Meeting meeting = meetingRepository.findById(meetingId)
                                           .orElseThrow(() -> new RuntimeException("meeting with id " +meetingId+" not found"));


        meetingRepository.deleteById(meetingId);

        return meeting.getId();

    }

    private AppUserResponse getLogInUser() {
        return Objects.requireNonNull(userFeignClient.getUserLoginUser().getBody());
        //return Objects.requireNonNull(userFeignClient.getAllUserByUserNames(usernames).getBody()).getData();
    }
    private AppUserResponse getUserById(Long id) {
        return Objects.requireNonNull(userFeignClient.getUserById(id).getBody());

    }

    private Attendee appUserResponseToAppUserDto(AppUserResponse response){

        Attendee appUser=new Attendee();
        appUser.setUserId(response.getId());
        appUser.setUsername(response.getUsername());
        appUser.setEmail(response.email);

        return appUser;

    }



}
