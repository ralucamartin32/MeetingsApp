package edu.arobs.meetingsapp.proposal;

import edu.arobs.meetingsapp.TimeSetter.TimeSetter;
import edu.arobs.meetingsapp.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity(name = "Proposal")
//@Table(name = "proposal")
@Setter
@Getter
public class Proposal extends TimeSetter {

    @Id
    @GeneratedValue
    @NotNull
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String author;

    private enum Type
    {
        PRESENTATION, PROJECT, CLIENT_DISCUSSION;
    }

    private Integer maxPersons;
    private String description;

    private enum Difficulty
    {
        LOW, MEDIUM, HIGH;
    }

    private String language;
    private String duration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proposal )) return false;
        return id != null && id.equals(((Proposal) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
