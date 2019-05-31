package edu.arobs.meetingsapp.topic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.arobs.meetingsapp.timeSetter.TimeSetter;
import edu.arobs.meetingsapp.vote.Vote;
import edu.arobs.meetingsapp.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

enum Type
{
    Presentation, Workshop, PROJECT, CLIENT_DISCUSSION;
}
@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Topic extends TimeSetter {

    private boolean isUserPresenter;
    private Integer sumOfVotes;
    private String topicContent;
    private String topicTitle;
    private Type topicType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "topic",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Vote> usersVotes = new ArrayList<>();
}
