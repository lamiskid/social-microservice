package com.project.payload;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {


    private Long userId;
    private String title;

    private String description;
    private LocalDate endAt;




}
