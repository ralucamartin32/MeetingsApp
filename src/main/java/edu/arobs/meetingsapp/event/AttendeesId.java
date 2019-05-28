package edu.arobs.meetingsapp.event;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class AttendeesId implements Serializable {

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "user_id")
    private Integer userId;

    public AttendeesId() {
    }

    public AttendeesId(Integer eventId, Integer userId) {
        this.eventId = eventId;
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        AttendeesId that = (AttendeesId) o;
        return Objects.equals(eventId, that.eventId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, userId);
    }
}
