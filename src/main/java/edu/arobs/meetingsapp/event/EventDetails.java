package edu.arobs.meetingsapp.event;

import edu.arobs.meetingsapp.TimeSetter.TimeSetter;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

enum Type {
    PRESENTATION, PROJECT, CLIENT_DISCUSSION;
}

enum Difficulty {
    LOW, MEDIUM, HIGH;
}

@Entity
@Data
public class EventDetails extends TimeSetter {

    @Id
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Event event;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Type type;

    private String language;
    private String duration;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Difficulty difficulty;

    private Date date;
    private Time time;
    private Integer maxPersons;
    private String description;
}
