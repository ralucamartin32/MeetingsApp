package edu.arobs.meetingsapp.Participant;

import edu.arobs.meetingsapp.meeting.Meeting;
import edu.arobs.meetingsapp.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "participant")
@Getter
@Setter
public class Participant {

    @EmbeddedId
    private ParticipantId id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("idMeeting")
//    private Meeting meeting;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("idUser")
//    private User user;
//
//    private Participant() {
//    }
//
//    public Participant(Meeting meeting, User user) {
//        this.meeting = meeting;
//        this.user = user;
//        this.id = new ParticipantId(meeting.getId(), user.getId());
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Participant that = (Participant) o;
//        return Objects.equals(meeting, that.meeting) && Objects.equals(user, that.user);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(meeting, user);
//    }
}
