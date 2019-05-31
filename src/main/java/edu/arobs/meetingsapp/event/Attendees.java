package edu.arobs.meetingsapp.event;

import edu.arobs.meetingsapp.user.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table
@Data
public class Attendees  {

    @EmbeddedId
    private AttendeesId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("eventId")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    private Timestamp registrationDate;

    public Attendees(){}

    public Attendees(Event event, User user) {
        this.event = event;
        this.user = user;
        this.id = new AttendeesId(event.getId(), user.getId());
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Attendees that = (Attendees) o;
        return Objects.equals(event, that.event) &&
                Objects.equals(user, that.user);

    }
    @Override
    public int hashCode(){
        return Objects.hash(event, user);
    }
}
