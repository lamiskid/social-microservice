package com.auth.config;



import com.auth.model.AppUser;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@AllArgsConstructor
public class AppUserDetails implements UserDetails {

    private String username;
    private String password;

    private boolean enable;
    private List<GrantedAuthority> authorities;


    @Autowired
    public AppUserDetails(AppUser appUser) {
        username = appUser.getUsername();
        password = appUser.getPassword();
        enable = appUser.isEnable();
       authorities =  appUser.getRoles()
                           .stream()
                           .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                           .collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
