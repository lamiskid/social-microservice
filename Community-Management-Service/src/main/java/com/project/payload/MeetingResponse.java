package com.project.payload;


import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeetingResponse {


    private String meetingName;

    private LocalDate meetUpDate;;

    private String location;

    private String city;


    private AppUserResponse organizer;

    private Set<AppUserResponse> attendees ;
}
