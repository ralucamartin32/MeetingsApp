package edu.arobs.meetingsapp.user;
import io.swagger.annotations.ApiModelProperty;
import  lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="userSeqGen")
    @SequenceGenerator(name = "userSeqGen", sequenceName = "USER_SEQ_GEN")
    @NotNull
    @ApiModelProperty(hidden = true)
    private Long id;
    private String firstName;
    private String lastName;
}
