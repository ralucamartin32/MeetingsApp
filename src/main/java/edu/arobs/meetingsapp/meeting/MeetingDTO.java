package edu.arobs.meetingsapp.meeting;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class MeetingDTO {

    private LocalDateTime date;
    private String title;
    private String location;
}
