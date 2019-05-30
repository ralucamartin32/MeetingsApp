package edu.arobs.meetingsapp.Feedback;

import lombok.Data;

@Data
public class FeedbackDTO {

    String clarity;
    String originality;
    String complexity;
    String engagement;
    String cursive;
    Integer usersId;
}
