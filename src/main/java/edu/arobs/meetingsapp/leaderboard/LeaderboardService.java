package edu.arobs.meetingsapp.leaderboard;

import edu.arobs.meetingsapp.event.EventService;
import edu.arobs.meetingsapp.user.User;
import edu.arobs.meetingsapp.user.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeaderboardService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EventService.class);
    private final LeaderboardRepository leaderboardRepository;
    private final UserRepository userRepository;
    private final LeaderboardModelMapper leaderboardModelMapper;
    @Autowired
    public LeaderboardService(LeaderboardRepository leaderboardRepository, UserRepository userRepository, LeaderboardModelMapper leaderboardModelMapper){
        this.leaderboardRepository = leaderboardRepository;
        this.userRepository = userRepository;
        this.leaderboardModelMapper = leaderboardModelMapper;
    }

    @Transactional
    public LeaderboardDTO create(Integer userId, LeaderboardDTO leaderboardDTO){

        Leaderboard leaderboard = new Leaderboard();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", userId)));
        leaderboard.setId(null);
        leaderboard.setUser(user);
        leaderboardRepository.save(leaderboard);
        return leaderboardModelMapper.fromEntityToDto(leaderboard);

    }

    public List<LeaderboardDTO> getAll() {

        List<LeaderboardDTO> leaderboardDTOS = new ArrayList<>();
        LeaderboardDTO leaderboardDTO;
        for(Leaderboard leaderboard : leaderboardRepository.findAll()){
            leaderboardDTO = leaderboardModelMapper.fromEntityToDto(leaderboard);
            leaderboardDTOS.add(leaderboardDTO);
        }
        return  leaderboardDTOS;
    }
}
