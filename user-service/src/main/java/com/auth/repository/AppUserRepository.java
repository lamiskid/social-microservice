package com.lamiskid.OAuth2project.repository;


import com.lamiskid.OAuth2project.model.AppUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}