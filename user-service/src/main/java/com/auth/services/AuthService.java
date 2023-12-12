package com.lamiskid.OAuth2project.services;


import com.lamiskid.OAuth2project.config.AppUserDetails;
import com.lamiskid.OAuth2project.config.JwtUtils;
import com.lamiskid.OAuth2project.config.UserDetailServiceImpl;
import com.lamiskid.OAuth2project.model.AppUser;
import com.lamiskid.OAuth2project.model.ERole;
import com.lamiskid.OAuth2project.payload.AuthRequest;
import com.lamiskid.OAuth2project.payload.LoginRequest;
import com.lamiskid.OAuth2project.payload.LoginResponse;
import com.lamiskid.OAuth2project.repository.AppUserRepository;
import com.lamiskid.OAuth2project.repository.RoleRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository appUserRepository;

    private final RoleRepository roleRepository;

    @Autowired
    private final PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

   private final JwtUtils jwtUtils;




    public void createUser(AuthRequest authRequest){

      if (appUserRepository.existsByUsername(authRequest.getUsername())){
          throw new RuntimeException("Username already exist");
      }
        if (appUserRepository.existsByUsername(authRequest.getEmail())){
            throw new RuntimeException("email already exist");
        }


        AppUser appUser=AppUser.builder()
                .email(authRequest.getEmail())
                .username(authRequest.getUsername())
                .password(encoder.encode(authRequest.getPassword()))
                .roles(Collections.singleton(roleRepository.findByName(ERole.ROLE_USER).get()))
                               .build();
        appUserRepository.save(appUser);

    }

    public LoginResponse signin(LoginRequest authRequest){
        System.out.println(authRequest);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        System.out.println(authentication.isAuthenticated());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) {

            AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                                            .map(item -> item.getAuthority())
                                            .collect(Collectors.toList());

            LoginResponse  loginResponse = LoginResponse.builder().username(userDetails.getUsername())
                                            .token(jwtUtils.generateToken(authRequest.getUsername()))
                                            .role(roles)
                                            .build();

            return loginResponse;




        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }



    public LoginResponse loginResponse(LoginRequest loginRequest) {

        AppUser appUser = appUserRepository.findByUsername(loginRequest.getUsername()).get();
        System.out.println(appUser);




            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));


            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateToken(loginRequest.getUsername());

            AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                                            .map(item -> item.getAuthority())
                                            .collect(Collectors.toList());


            return LoginResponse.builder()
                                .token(jwt)
                                .username(userDetails.getUsername())
                                .role(roles).build();

    }



}
