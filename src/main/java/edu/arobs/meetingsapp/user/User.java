package edu.arobs.meetingsapp.user;
import edu.arobs.meetingsapp.Participant.Participant;
import io.swagger.annotations.ApiModelProperty;
import  lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
@NaturalIdCache
@Data
@Getter
@Setter
public class User {

    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="userSeqGen")
//    @SequenceGenerator(name = "userSeqGen", sequenceName = "USER_SEQ_GEN")
    @GeneratedValue
    @NotNull
//    @ApiModelProperty(hidden = true)
    private Long id;

//    @NaturalId
    private String firstName;

//    @NaturalId
    private String lastName;

    @OneToMany(mappedBy = "user",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<Participant> participants = new ArrayList<>();

    public User(){

    }
    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
