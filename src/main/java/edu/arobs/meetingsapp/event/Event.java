package edu.arobs.meetingsapp.event;

import edu.arobs.meetingsapp.TimeSetter.TimeSetter;
import edu.arobs.meetingsapp.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Event extends TimeSetter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
