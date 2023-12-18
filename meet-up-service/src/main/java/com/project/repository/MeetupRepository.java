package com.project.repository;


import com.project.model.MeetUp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MeetupRepository extends JpaRepository<MeetUp,Long> {

        //List<MeetUp> findByUserId(Long userId);

}
