package edu.arobs.meetingsapp.proposal;

import edu.arobs.meetingsapp.user.User;
import edu.arobs.meetingsapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProposalModelMapper {

    private final UserRepository userRepository;

    @Autowired
    public ProposalModelMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Proposal fromDtoToEntity(Integer userId, ProposalDTO proposalDTO) {

        Proposal proposal = new Proposal();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", userId)));
        proposal.setUser(user);
        proposal.setTitle(proposalDTO.getTitle());
        proposal.setAuthor(proposalDTO.getAuthor());///???
        proposal.setMaxPersons(proposalDTO.getMaxPersons());
        proposal.setDescription(proposalDTO.getDescription());
        proposal.setLanguage(proposalDTO.getLanguage());
        proposal.setDuration(proposalDTO.getDuration());
        return proposal;
    }

    public ProposalDTO fromEntityToDTO(Proposal proposal) {

        ProposalDTO proposalDTO = new ProposalDTO();
        proposalDTO.setTitle(proposal.getTitle());
        proposalDTO.setAuthor(proposal.getAuthor());
        proposalDTO.setMaxPersons(proposal.getMaxPersons());
        proposalDTO.setDescription(proposal.getDescription());
        proposalDTO.setLanguage(proposal.getLanguage());
        proposalDTO.setDuration(proposal.getDuration());
        return proposalDTO;
    }
}
