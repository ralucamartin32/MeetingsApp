package edu.arobs.meetingsapp.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.arobs.meetingsapp.timeSetter.TimeSetter;
import lombok.Data;

import javax.persistence.Entity;


@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends TimeSetter {

    private String fullName;
    private String email;
    private String password;
    private Integer points;
    private String token;
}
