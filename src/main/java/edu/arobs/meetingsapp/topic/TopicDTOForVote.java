package edu.arobs.meetingsapp.topic;

import edu.arobs.meetingsapp.vote.Vote;
import edu.arobs.meetingsapp.vote.VoteDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TopicDTOForVote {
    private Integer id;
    private boolean isUserPresenter;
    private Integer sumOfVotes;
    private String topicContent;
    private String topicTitle;
    private Type topicType;
    private List<VoteDTO> userVotes = new ArrayList<>();
    private Integer usersId;
}
