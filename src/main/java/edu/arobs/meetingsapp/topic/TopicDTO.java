package edu.arobs.meetingsapp.topic;

import edu.arobs.meetingsapp.vote.Vote;


import java.util.ArrayList;
import java.util.List;

public class TopicDTO {
    private boolean isUserPresenter;
    private Integer sumOfVotes;
    private String topicContent;
    private String topicTitle;
    private Type topicType;
    private List<Vote> userVotes = new ArrayList<>();
    private Integer usersId;
}
