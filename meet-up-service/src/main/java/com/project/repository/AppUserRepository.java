package com.project.repository;

import com.project.model.Attendee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<Attendee,Long> {

//    Boolean existsByUsername(List<String> username);

   /* @Query("SELECT COUNT(u) FROM User u WHERE u.username IN :usernames")
    long countUsersByIds(@Param("usernames") List<Long> userIds);*/

    List<Attendee> findByUsername(String username);
}
