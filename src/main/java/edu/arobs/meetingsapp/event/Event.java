package edu.arobs.meetingsapp.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.arobs.meetingsapp.Feedback.Feedback;
import edu.arobs.meetingsapp.TimeSetter.TimeSetter;
import edu.arobs.meetingsapp.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Event extends TimeSetter {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(
            mappedBy = "event",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Attendees> attendanceSetId = new HashSet<>();


    @JsonIgnore
    @OneToMany(
            mappedBy = "event",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Feedback> feedbacks = new HashSet<>();


    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
        feedback.setEvent(this);
    }

    public void removeFeedBack(Feedback feedBack) {
        feedbacks.remove(feedBack);
        feedBack.setEvent(null);
    }

}
