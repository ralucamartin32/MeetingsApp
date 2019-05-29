package edu.arobs.meetingsapp.leaderboard;

import edu.arobs.meetingsapp.event.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaderboards")
public class LeaderboardController {

    private LeaderboardService leaderboardService;
    @Autowired
    public LeaderboardController(LeaderboardService leaderboardService){
        this.leaderboardService = leaderboardService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public LeaderboardDTO create(@RequestParam(required = true) Integer userId, @RequestBody LeaderboardDTO leaderboardDTO) {
        return leaderboardService.create(userId, leaderboardDTO);
    }

    @GetMapping
    @ResponseBody
    public List<LeaderboardDTO> getAll(){
        return leaderboardService.getAll();
    }
}
