package edu.arobs.meetingsapp.topic;

import edu.arobs.meetingsapp.vote.VoteDTO;
import lombok.Data;

import java.util.List;

@Data
public class PostPatchDTO {

    private Integer sumOfVotes;
    private List<VoteDTO> userVotes;
}
