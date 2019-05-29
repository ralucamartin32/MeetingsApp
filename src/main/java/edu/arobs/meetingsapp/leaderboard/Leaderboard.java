package edu.arobs.meetingsapp.leaderboard;

import edu.arobs.meetingsapp.TimeSetter.TimeSetter;
import edu.arobs.meetingsapp.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Leaderboard extends TimeSetter {

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;
}
