package edu.arobs.meetingsapp.proposal;

import edu.arobs.meetingsapp.user.User;
import edu.arobs.meetingsapp.user.UserRepository;
import edu.arobs.meetingsapp.user.UserService;
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
    public ProposalService(ProposalRepository proposalRepository, UserRepository userRepository, ProposalModelMapper proposalModelMapper, ModelMapper modelMapper) {
        this.proposalRepository = proposalRepository;
        this.userRepository = userRepository;
        this.proposalModelMapper = proposalModelMapper;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public ProposalDTOForCreate create(Integer userId, ProposalDTOForCreate proposalDTO) {

        Proposal proposal = new Proposal();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Proposal id=%d does not exist", userId)));
        proposal.setUser(user);
        modelMapper.map(proposalDTO, proposal);
        proposal.setAuthor(user.getFullName());
        proposal.setId(null);
        proposalRepository.save(proposal);
        LOGGER.info("Successfully created " + proposal);
        ProposalDTOForCreate pDTO = new ProposalDTOForCreate();
        modelMapper.map(proposal, pDTO);
        return pDTO;
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
    public ProposalDTO update(Integer userId, Integer proposalId, ProposalDTO proposalDTO) {
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
    public List<ProposalDTO> getAllProposals() {
        List<ProposalDTO> proposalDTOS = new ArrayList<>();
        ProposalDTO pDTO = new ProposalDTO();
        for (Proposal p : proposalRepository.findAll()) {
            modelMapper.map(p, pDTO);
            proposalDTOS.add(pDTO);
        }

        return proposalDTOS;
    }


    @Transactional
    public List<ProposalDTO> getProposalsForUser(Integer userId) {
        List<Proposal> proposals;
        proposals = proposalRepository.getProposalsForUser(userId);

        return getProposalDTOS(proposals);
    }

    @Transactional
    public List<ProposalDTO> getProposalsByYear(Integer year) {
        List<Proposal> proposals;
        proposals = proposalRepository.getProposalsByYear(year);

        return getProposalDTOS(proposals);
    }

    @Transactional
    private List<ProposalDTO> getProposalDTOS(List<Proposal> proposals) {
        ProposalDTO pDTO = new ProposalDTO();
        List<ProposalDTO> proposalDTOS = new ArrayList<>();
        for (Proposal p : proposals) {
            modelMapper.map(p, pDTO);
            proposalDTOS.add(pDTO);
        }

        return proposalDTOS;
    }

    @Transactional
    public List<ProposalsFromUsersForYearDTO> getProposalsFromUserForYear(Integer userId, Integer year) {
        List<ProposalsFromUsersForYearDTO> pDTOs;
        pDTOs = proposalRepository.getProposalsFromUserForYear(userId, year);
        return pDTOs;
    }

}
