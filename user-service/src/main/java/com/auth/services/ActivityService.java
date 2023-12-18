package com.auth.services;


import com.auth.payload.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ActivityService {


    @Autowired
    private RestTemplate restTemplate;
    public void p(){

        restTemplate.getForObject("", LoginRequest.class);

    }



}
