package edu.arobs.meetingsapp.event;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class EventDTO {

    private String name;
    private Type type;
    private String language;
    private String duration;
    private Difficulty difficulty;
    private LocalDateTime date;
    private LocalDateTime time;
    private Integer maxPersons;
    private String description;
}
