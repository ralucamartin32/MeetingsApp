package edu.arobs.meetingsapp.proposal;

import lombok.Data;

@Data
public class ProposalDTOForCreate {

    private String title;
    private Type type;
    private Integer maxPersons;
    private String description;
    private Difficulty difficulty;
    private String language;
    private String duration;
}
