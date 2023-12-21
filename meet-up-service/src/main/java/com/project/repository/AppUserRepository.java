package com.project.repository;

import com.project.model.AppUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

//    Boolean existsByUsername(List<String> username);

    @Query("SELECT COUNT(u) FROM User u WHERE u.username IN :usernames")
    long countUsersByIds(@Param("usernames") List<Long> userIds);

    List<AppUser> findByUsername(String username);
}
