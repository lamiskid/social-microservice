package com.project.reposittory;



import com.project.model.Schedule;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    List<Schedule> findByAppUserId(Long userId);

}
