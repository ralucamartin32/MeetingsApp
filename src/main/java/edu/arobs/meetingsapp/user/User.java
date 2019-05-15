package edu.arobs.meetingsapp.user;

import edu.arobs.meetingsapp.TimeSetter.TimeSetter;
import edu.arobs.meetingsapp.proposal.Proposal;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
//@Table(name = "user")
@Getter
@Setter
public class User extends TimeSetter {

    @Id
    @GeneratedValue
    @NotNull
    private Integer id;
    private String fullName;
    private String email;
    private String password;
    private Integer points;
    private String token;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Proposal> proposals = new ArrayList<>();

    public void addProposal(Proposal proposal) {
        proposals.add(proposal);
        proposal.setUser(this);
    }
}
