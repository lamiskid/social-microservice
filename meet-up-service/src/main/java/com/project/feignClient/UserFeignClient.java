package com.project.feignClient;


import com.project.model.AppUser;
import com.project.payload.ApiResponse;
import com.project.payload.AppUserResponse;
import java.util.List;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-SERVICE")
//@LoadBalancerClient(name = "USER-SERVICE")
public interface UserFeignClient {
  /*  @GetMapping("/api/v1/users/username/{username}")
    ResponseEntity<ApiResponse<AppUser>> getUserByUserName(@PathVariable String username);*/


    @GetMapping("/api/v1/user/getAllUsers")
    ResponseEntity<ApiResponse<AppUser>> getAllUser();

    @GetMapping("/api/v1/user/by-usernames")
    ResponseEntity<List<AppUserResponse>> getAllUserByUserNames(@RequestParam List<String> usernames);

}

