package com.auth.services;

import com.auth.config.AppUserDetails;
import com.auth.model.AppUser;
import com.auth.payload.AppUserDto;
import com.auth.repository.AppUserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService {

    public AppUserRepository appUserRepository;
    public AppUserDto authenticatedUser() {

       AppUser appUser= appUserRepository.findByUsername(getAuthenticatedUser())
               .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));

        AppUserDto appUserDto = AppUserDto.builder()
                .id(appUser.getId())
                .username(appUser.getUsername())
                .email(appUser.getEmail()).build();
       return appUserDto;
    }





    public String getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();

            return userDetails.getUsername();

    }


    public List<AppUserDto> getUsersByUserNames(List<String> username){

        List<AppUserDto> userDtoList = appUserRepository.findByUsernames(username)
                                                        .stream().map(this::convertAppUserToDto)
                                                        .collect(Collectors.toList());
        return userDtoList;

    }


    public List<AppUserDto> getAllUsers() {
        List<AppUserDto> userDtoList = appUserRepository.findAll().
                                                        stream().map(this::convertAppUserToDto)
                                                        .collect(Collectors.toList());
        return userDtoList;

    }

    public AppUserDto convertAppUserToDto(AppUser appUser) {
        return AppUserDto.builder().
                         email(appUser.getEmail())
                         .username(appUser.getUsername())
                         .id(appUser.getId())
                         .build();
    }
}
