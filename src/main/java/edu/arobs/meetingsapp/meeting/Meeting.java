package edu.arobs.meetingsapp.meeting;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Entity
@Data
public class Meeting {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="meetingSeqGen")
    @SequenceGenerator(name = "meetingSeqGen", sequenceName = "MEETING_SEQ_GEN")
    @NotNull
    private Long id;
    private LocalDateTime date;
    private String title;
    private String location;
}
