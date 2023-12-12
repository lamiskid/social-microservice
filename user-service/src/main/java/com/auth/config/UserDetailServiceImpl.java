package com.lamiskid.OAuth2project.config;

import com.lamiskid.OAuth2project.model.AppUser;
import com.lamiskid.OAuth2project.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private  AppUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                                     .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return new AppUserDetails(user);
    }

}