package edu.arobs.meetingsapp.leaderboard;

import edu.arobs.meetingsapp.user.UserModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LeaderboardModelMapper {

    @Autowired
    private final UserModelMapper userModelMapper;
    
    public LeaderboardModelMapper(UserModelMapper userModelMapper) {

        this.userModelMapper = userModelMapper;
    }

    public Leaderboard fromDtoToEntity(LeaderboardDTO leaderboardDTO) {

        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setId(leaderboard.getId());
        leaderboard.setUser(userModelMapper.fromDtoToEntity(leaderboardDTO.getUsers()));
        return leaderboard;
    }

    public LeaderboardDTO fromEntityToDto(Leaderboard leaderboard) {

        LeaderboardDTO leaderboardDTO = new LeaderboardDTO();
        leaderboardDTO.setId(leaderboard.getId());
        leaderboardDTO.setUsers(userModelMapper.fromEntityToDTO(leaderboard.getUser()));
        leaderboardDTO.setUsersId(leaderboard.getUser().getId());
        return leaderboardDTO;
    }
}
