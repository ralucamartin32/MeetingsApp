package edu.arobs.meetingsapp.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.arobs.meetingsapp.TimeSetter.TimeSetter;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends TimeSetter {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
    private String fullName;
    private String email;
    private String password;
    private Integer points;
    private String token;

//    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<Proposal> proposals = new ArrayList<>();

//    public void addProposal(Proposal proposal) {
//        proposals.add(proposal);
//        proposal.setUser(this);
//    }
}
