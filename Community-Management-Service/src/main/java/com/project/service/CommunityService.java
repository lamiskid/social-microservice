package com.project.service;


import com.project.feignClient.ChatFeignClient;
import com.project.feignClient.MeetingFeignClient;
import com.project.feignClient.UserFeignClient;
import com.project.payload.AppUserResponse;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommunityService {

    private final UserFeignClient userFeignClient;

    private final MeetingFeignClient meetingFeignClient;

    private final ChatFeignClient chatFeignClient;

    public List<AppUserResponse> getAllUsers(){
       return getUserAllUsers();
    }

    public String removeUser(Long userId){
        return deleteUser(userId);
    }

    public void toEnableUser(Boolean enable, Long userid){
            enableUser(enable,userid);
    }

    public Long deleteMeeting(Long meetingId){
     return Objects.requireNonNull(meetingFeignClient.deleteMeetings(meetingId)).getBody();
    }

    public Object getAllMeetings(){
        return Objects.requireNonNull(meetingFeignClient.getAllMeetings()).getBody();
    }

    public Object getAllChatGroup(){
     return  Objects.requireNonNull(chatFeignClient.allGroupChat().getBody());
    }

    public Long deleteChatGroup(Long groupId){
      return   Objects.requireNonNull(chatFeignClient.deleteGroup(groupId).getBody());
    }



    public String deleteUser(Long id) {
        return Objects.requireNonNull(userFeignClient.deleteUser(id).getBody());

    }


    public String enableUser(Boolean enable, Long userId) {
        return Objects.requireNonNull(userFeignClient.blockUser(enable, userId).getBody());

    }


    public AppUserResponse getUserById(Long id) {
        return Objects.requireNonNull(userFeignClient.getUserById(id).getBody());

    }

    public List<AppUserResponse> getUserAllUsers() {
        return Objects.requireNonNull(userFeignClient.getAllUser().getBody());

    }



}
