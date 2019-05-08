package edu.arobs.meetingsapp.Participant;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Data
public class Participant {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="participantSeqGen")
    @SequenceGenerator(name = "participantSeqGen", sequenceName = "PARTICIPANT_SEQ_GEN")
    @NotNull
    private Long id;
    private Long idMeeting;
    private Long idUser;
}
