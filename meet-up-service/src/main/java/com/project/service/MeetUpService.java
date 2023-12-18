package com.project.service;

import com.project.feignClient.UserFeignClient;
import com.project.model.AppUser;
import com.project.model.MeetUp;
import com.project.payload.AddUserRequest;
import com.project.payload.AppUserResponse;
import com.project.payload.CreateMeetUpRequest;
import com.project.repository.MeetupRepository;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MeetUpService {

    private final MeetupRepository meetupRepository;

    private final WebClient.Builder webClientBuilder;

    @Autowired
    private final UserFeignClient userFeignClient;



    public void createMeetUp(CreateMeetUpRequest meetUpRequest,Long userId){

        MeetUp meetUp= MeetUp.builder()
                .meetingName(meetUpRequest.getMeetingName())
                .city(meetUpRequest.getCity())
                .location(meetUpRequest.getLocation())
                .meetUpDate(meetUpRequest.getMeetUpDate())
                .ownerId(userId)
              .build();

        meetupRepository.save(meetUp);


    }
    @Transactional
    public void addUsersToMeetUp(Long meeUpId, List<String> usernames){

      MeetUp meetUp=  meetupRepository.findById(meeUpId)
                                .orElseThrow(() -> new RuntimeException("Meeting not found"));


      //boolean isAdded =  meetUp.getUsers().addAll(getAllUserByUsername(usernames));

        List<AppUserResponse> allUserByUsername = getAllUserByUsername(usernames);
        List<AppUser> appUserStream = allUserByUsername.stream().map(this::toAppUserResponse).collect(Collectors.toList());

       boolean isAdded= meetUp.getUsers().addAll(appUserStream);

      if(isAdded){
          meetupRepository.save(meetUp);
      }
      else {
        throw new RuntimeException("could not add users");
      }







    }

    public AppUser toAppUserResponse(AppUserResponse  userResponse){
       return AppUser.builder().userId(userResponse.getId()).username(userResponse.getUsername()).build();
    }

    public AppUser getAllUser() {
       return Objects.requireNonNull(userFeignClient.getAllUser().getBody()).getData();
    }

    public List<AppUserResponse> getAllUserByUsername(List<String> usernames) {
     return  Objects.requireNonNull(userFeignClient.getAllUserByUserNames(usernames).getBody());
        //return Objects.requireNonNull(userFeignClient.getAllUserByUserNames(usernames).getBody()).getData();
    }

    public List<MeetUp> findMyMeetUps(Long userId){

        return meetupRepository.findAllById(Collections.singleton(userId));

    }
    public List<MeetUp> getAllMyMeetup(){


        return meetupRepository.findAll();

    }
    @Transactional
    public void deleteMeetUp(Long meetUpId){

       MeetUp meetUp= meetupRepository.findById(meetUpId).get();
        meetupRepository.delete(meetUp);
    }
    @Transactional
    public void removeUserInMeetUp(){

    }

    public CreateMeetUpRequest getLoginUser(){

        Mono<CreateMeetUpRequest> loginUser = webClientBuilder.build()
                                                                  .get()
                                                                  .uri("")
                                                                  .retrieve()
                                                                  .bodyToMono(CreateMeetUpRequest.class)
                                                                  .doOnError(throwable -> new RuntimeException("Can not get user"));
        return loginUser.block();


    }

}
