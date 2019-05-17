package edu.arobs.meetingsapp.proposal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public interface ProposalsFromUsersForYearDTO {

//    private String fullName;
//    private String title;
    String getFullName();

    String getTitle();

}
