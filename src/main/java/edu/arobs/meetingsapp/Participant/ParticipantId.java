package edu.arobs.meetingsapp.Participant;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
public class ParticipantId implements Serializable {

    @Column(name = "id_meeting")
    private Long idMeeting;

    @Column(name = " id_user")
    private Long idUser;

    private ParticipantId() {
    }

    public ParticipantId(Long idMeeting, Long idUser) {
        this.idMeeting = idMeeting;
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return  false;

        ParticipantId that = (ParticipantId) o;
        return Objects.equals(idMeeting, that.idMeeting) && Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode(){
        return Objects.hash(idMeeting, idUser);
    }



}
