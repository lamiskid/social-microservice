package com.project.payload;


import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateMeeting {


    private String meetingName;

    private LocalDate meetUpDate;;

    private String location;

    private String city;


}
