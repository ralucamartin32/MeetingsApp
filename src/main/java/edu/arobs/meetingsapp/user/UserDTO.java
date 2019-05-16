package edu.arobs.meetingsapp.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {


    private String fullName;
    private String email;
    private String password;
    private Integer points;
    private String token;
}
