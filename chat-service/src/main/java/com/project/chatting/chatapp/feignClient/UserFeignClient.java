package com.project.chatting.chatapp.feignClient;


import com.project.chatting.chatapp.payload.AppUserResponse;
import java.util.List;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-SERVICE")
@LoadBalancerClient(name = "USER-SERVICE")
public interface UserFeignClient {
    @GetMapping("/api/v1/users/login-user")
    ResponseEntity<AppUserResponse> getUserLoginUser();


    @GetMapping("/api/v1/user/getAllUsers")
    ResponseEntity<List<AppUserResponse>> getAllUser();

    @GetMapping("/api/v1/user/{id}")
    ResponseEntity<AppUserResponse> getUserById(@PathVariable("id") Long id);

    @GetMapping("/api/v1/user/by-usernames")
    ResponseEntity<List<AppUserResponse>> getAllUserByUserNames(@RequestParam List<String> usernames);

}

