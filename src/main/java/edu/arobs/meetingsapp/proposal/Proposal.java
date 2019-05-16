package edu.arobs.meetingsapp.proposal;

import edu.arobs.meetingsapp.TimeSetter.TimeSetter;
import edu.arobs.meetingsapp.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

enum Difficulty
{
    LOW, MEDIUM, HIGH;
}

enum Type
{
    PRESENTATION, PROJECT, CLIENT_DISCUSSION;
}

@Entity(name = "Proposal")
@Data
@Setter
@Getter
public class Proposal extends TimeSetter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String author;


    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Type type;

    private Integer maxPersons;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Difficulty difficulty;

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
