package edu.arobs.meetingsapp.proposal;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import edu.arobs.meetingsapp.user.*;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProposalService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final ProposalRepository proposalRepository;
    private final UserRepository userRepository;
    private final ProposalModelMapper proposalModelMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public ProposalService(ProposalRepository proposalRepository, UserRepository userRepository,ProposalModelMapper proposalModelMapper, ModelMapper modelMapper) {
        this.proposalRepository = proposalRepository;
        this.userRepository = userRepository;
        this.proposalModelMapper = proposalModelMapper;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public ProposalDTO create(Integer userId, ProposalDTO proposalDTO) {

        Proposal proposal = new Proposal();
        modelMapper.map(proposalDTO, proposal);
        proposal.setId(null);
        proposalRepository.save(proposal);
        LOGGER.info("Successfully created " + proposal);
        return proposalDTO;
    }

    @Transactional
    public ProposalDTO getById(Integer id) {
        Proposal proposal = proposalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Proposal id=%d does not exist", id)));
        ProposalDTO proposalDTO = new ProposalDTO();
        modelMapper.map(proposal, proposalDTO);
        return proposalDTO;

    }

    @Transactional
    public ProposalDTO update(Integer userId, Integer proposalId, ProposalDTO proposalDTO){
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Proposal id=%d does not exist", proposalId)));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", userId)));
        proposal.setUser(user);
        modelMapper.map(proposalDTO, proposal);
        proposal.setTitle(proposal.getTitle());
        proposal.setAuthor(proposal.getAuthor());
        proposal.setType(proposal.getType());
        proposal.setMaxPersons(proposal.getMaxPersons());
        proposal.setDescription(proposal.getDescription());
        proposal.setDifficulty(proposal.getDifficulty());
        proposal.setLanguage(proposal.getLanguage());
        proposal.setDuration(proposal.getDuration());
        ProposalDTO response = new ProposalDTO();
        modelMapper.map(proposal, response);
        return response;
    }

    @Transactional
    public ProposalDTO delete(Integer id) {
        Proposal existingProposal = proposalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", id)));
        proposalRepository.deleteById(id);
        ProposalDTO proposalDTO = new ProposalDTO();
        modelMapper.map(existingProposal, proposalDTO);
        return proposalDTO;
    }

    @Transactional
    public List<Proposal> getProposalsFromUser(Integer userId){
        Proposal proposal;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", userId)));
        proposal = proposalRepository.findByUser(user);
        List<Proposal> proposals = new ArrayList<>();
        proposals.add(proposal);
        return proposals;
    }
}
