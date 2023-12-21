package com.project.repository;


import com.project.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Long> {

        //List<MeetUp> findByUserId(Long userId);

}
