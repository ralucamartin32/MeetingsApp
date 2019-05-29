package edu.arobs.meetingsapp.leaderboard;

import edu.arobs.meetingsapp.user.UserDTO;
import lombok.Data;

@Data
public class LeaderboardDTO {

    private Integer id;
    private UserDTO users;
    private Integer usersId;

}
