package com.project.feignClient;

import com.project.payload.AppUserResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "USER-SERVICE")
public interface UserFeignClient {



    @GetMapping("/api/v1/user/getAllUsers")
    ResponseEntity<List<AppUserResponse>> getAllUser();

    @GetMapping("/api/v1/user/{id}")
    ResponseEntity<AppUserResponse> getUserById(@PathVariable("id") Long id);

    @GetMapping("/api/v1/user/by-usernames")
    ResponseEntity<List<AppUserResponse>> getAllUserByUserNames(@RequestParam List<String> usernames);


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id);


    @PostMapping("/enable/{userId}")
    public ResponseEntity<String> blockUser(@RequestParam("disable") Boolean enable,
                                            @PathVariable("userId") Long userId);

}