package edu.arobs.meetingsapp.meeting;

import edu.arobs.meetingsapp.Participant.Participant;
import edu.arobs.meetingsapp.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meeting")
@Setter
@Getter
public class Meeting {

    @Id
    @GeneratedValue
    @NotNull
    private Long id;
    private LocalDateTime date;
    private String title;
    private String location;
//
//    @OneToMany(mappedBy = "meeting",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//
//    private List<Participant> participants = new ArrayList<>();
//
//    public Participant addParticipant(User user){
//        Participant participant = new Participant(this, user);
//        participants.add(participant);
//        user.getParticipants().add(participant);
//        return participant;
//    }
//
////    public void removeParticipant(User user) {
////
////    }
}
