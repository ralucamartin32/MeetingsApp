package edu.arobs.meetingsapp.feedback;

import edu.arobs.meetingsapp.event.Event;
import edu.arobs.meetingsapp.timeSetter.TimeSetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Feedback extends TimeSetter {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    Event event;
    String clarity;
    String originality;
    String complexity;
    String engagement;
    String cursive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feedback)) return false;
        return super.getId() != null && super.getId().equals(((Feedback) o).getId());
    }
}
