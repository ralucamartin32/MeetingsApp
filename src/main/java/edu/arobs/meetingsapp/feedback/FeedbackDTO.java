package edu.arobs.meetingsapp.feedback;

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
