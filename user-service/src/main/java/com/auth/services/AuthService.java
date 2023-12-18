package com.auth.services;

import com.auth.config.AppUserDetails;
import com.auth.config.JwtUtils;
import com.auth.model.AppUser;
import com.auth.model.ERole;
import com.auth.payload.AuthRequest;
import com.auth.payload.CreateUserChat;
import com.auth.payload.LoginRequest;
import com.auth.payload.LoginResponse;
import com.auth.repository.AppUserRepository;
import com.auth.repository.RoleRepository;
import com.auth.util.WebConstant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.WebUtils;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository appUserRepository;

    private final RoleRepository roleRepository;

    @Autowired
    private final PasswordEncoder encoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    private final WebClient.Builder webClientBuilder;
    private final JwtUtils jwtUtils;


    public void createUser(AuthRequest authRequest) {

        if (appUserRepository.existsByUsername(authRequest.getUsername())) {
            throw new RuntimeException("Username already exist");
        }
        if (appUserRepository.existsByUsername(authRequest.getEmail())) {
            throw new RuntimeException("email already exist");
        }


        AppUser appUser = AppUser.builder()
                                 .email(authRequest.getEmail())
                                 .username(authRequest.getUsername())
                                 .password(encoder.encode(authRequest.getPassword()))
                                 .roles(Collections.singleton(roleRepository.findByName(ERole.ROLE_USER).get()))
                                 .build();

        CreateUserChat chat = new CreateUserChat(appUser.getUsername(), appUser.getEmail(), appUser.getEmail());
        signUpToChat(chat);

        appUserRepository.save(appUser);
    }

    public LoginResponse signin(LoginRequest authRequest) {
        System.out.println(authRequest);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        System.out.println(authentication.isAuthenticated());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) {

            AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                                            .map(item -> item.getAuthority())
                                            .collect(Collectors.toList());


            LoginResponse loginResponse = LoginResponse.builder().username(userDetails.getUsername())
                                                       .token(jwtUtils.generateToken(authRequest.getUsername()))
                                                       .role(roles)
                                                       .build();


            return loginResponse;

            //loginToChat();


        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }


    private void signUpToChat(CreateUserChat chat) {

        webClientBuilder.build()
                        .post()
                        .uri("https://api.chatengine.io/users/")
                        .header("PRIVATE-KEY", WebConstant.PRIVATE_KEY)
                        .bodyValue(chat)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, response -> response.bodyToMono(String.class).map(Exception::new))
                        .bodyToMono(CreateUserChat.class)
                        .doOnError(throwable -> new RuntimeException(throwable.getMessage()))
                        .subscribe(responseBody -> {
                            // Handle the response
                            System.out.println("Response: " + responseBody);
                        });
    }

    private void loginToChat(CreateUserChat chat) {

        webClientBuilder.build()
                        .get()
                        .uri("https://api.chatengine.io/users/me/")
                        .header("Project-ID", WebConstant.PROJECT_ID)
                        .header("User-Name", chat.getUsername())
                        .header("User-Secret", chat.getSecret())
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, response -> response.bodyToMono(String.class).map(Exception::new))
                        .bodyToMono(CreateUserChat.class)
                        .doOnError(throwable -> new RuntimeException(throwable.getMessage()));
    }
}
