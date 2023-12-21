/*
package com.project.service;

import com.project.feignClient.UserFeignClient;
import com.project.model.AppUser;
import com.project.model.Meeting;
import com.project.payload.AppUserResponse;
import com.project.payload.CreateMeeting;
import com.project.repository.AppUserRepository;
import com.project.repository.MeetingRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MeetUpService {

    private final MeetingRepository meetingRepository;

    private final AppUserRepository userRepository;

    private final WebClient.Builder webClientBuilder;

    @Autowired
    private final UserFeignClient userFeignClient;


    public void createMeetUp(CreateMeeting meetUpRequest, Long userId) {

        Meeting meeting = Meeting.builder()
                                 .meetingName(meetUpRequest.getMeetingName())
                                 .city(meetUpRequest.getCity())
                                 .location(meetUpRequest.getLocation())
                                 .meetUpDate(meetUpRequest.getMeetUpDate())
                                 // .ownerId(userId)
                                 .build();

        meetingRepository.save(meeting);


    }

    @Transactional
    public void addUsersToMeetUp(Long meeUpId, List<String> usernames) {

        Meeting meeting = meetingRepository.findById(meeUpId)
                                           .orElseThrow(() -> new RuntimeException("Meeting not found"));

        List<AppUserResponse> allUserByUsername = getAllUserByUsername(usernames);
        Set<AppUser> appUserStream = allUserByUsername.stream().map(this::toAppUserResponse).collect(Collectors.toSet());

        Set<AppUser> combinedList = new HashSet<>(appUserStream);
        boolean isAdded= combinedList.addAll(meeting.getAttendees());

        if (isAdded) {
            meetingRepository.save(meeting);
        } else {
            throw new RuntimeException("could not add users");
        }

    }



    public List<Meeting> getAllMyMeetup() {



        return meetingRepository.findAll();
    }

    @Transactional
    public void deleteMeetUp(Long meetUpId,String username) {

        Meeting meeting = meetingRepository.findById(meetUpId).get();
        meetingRepository.delete(meeting);
    }

    public List<Meeting> findMyMeetUps(String username) {

        List<Meeting> meetings =new ArrayList<>();

        userRepository.findByUsername(username).stream().map(appUser ->
                appUser.getMeetUps().stream().map(meetUp -> meetings.add(meetUp)));

        return meetings;


    }

    @Transactional
    public void removeUserInMeetUp(String username) {

     Meeting meeting =   meetingRepository.findById(1L)
                                          .orElseThrow(() -> new RuntimeException("You are not the creator of this meeting"));

     List<AppUser> appUser=   userRepository.findByUsername(username);

    }




    private AppUser toAppUserResponse(AppUserResponse userResponse) {
        return AppUser.builder().userId(userResponse.getId()).username(userResponse.getUsername()).build();
    }

    private AppUser getAllUser() {
        return Objects.requireNonNull(userFeignClient.getAllUser().getBody()).getData();
    }

    public List<AppUserResponse> getLogInUser() {
        return Objects.requireNonNull(userFeignClient.getUserLoginUser().getBody()).getData();
        //return Objects.requireNonNull(userFeignClient.getAllUserByUserNames(usernames).getBody()).getData();
    }


    public List<AppUserResponse> getAllUserByUsername(List<String> usernames) {
        return Objects.requireNonNull(userFeignClient.getAllUserByUserNames(usernames).getBody());
        //return Objects.requireNonNull(userFeignClient.getAllUserByUserNames(usernames).getBody()).getData();
    }


    public CreateMeeting getLoginUser() {

        Mono<CreateMeeting> loginUser = webClientBuilder.build()
                                                        .get()
                                                        .uri("")
                                                        .retrieve()
                                                        .bodyToMono(CreateMeeting.class)
                                                        .doOnError(throwable -> new RuntimeException("Can not get user"));
        return loginUser.block();


    }





}
*/
