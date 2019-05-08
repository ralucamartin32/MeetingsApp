package edu.arobs.meetingsapp.Participant;

import edu.arobs.meetingsapp.user.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;

@Service
public class ParticipantService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final ParticipantRepository participantRepository;
    private final ParticipantModelMapper participantModelMapper;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository, ParticipantModelMapper participantModelMapper) {
        this.participantRepository = participantRepository;
        this.participantModelMapper = participantModelMapper;
    }

    public ParticipantDTO create(ParticipantDTO participantDTO) {


        Participant participant = participantModelMapper.fromDtoToEntity(participantDTO);
        participant.setId(null);
        Participant p =participantRepository.save(participant);
        LOGGER.info("Successfully created " + participant);
        return participantModelMapper.fromEntityToDTO(p);
    }

    public ParticipantDTO getById(Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Participant id=%d does not exist", id)));
        return participantModelMapper.fromEntityToDTO(participant);
    }


    public ParticipantDTO update(Long id, ParticipantDTO participantDTO) {

        Participant existingParticipant = participantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Participant id=%d does not exist", id)));
        existingParticipant.setIdMeeting(participantModelMapper.fromDtoToEntity(participantDTO).getIdMeeting());
        existingParticipant.setIdUser(participantModelMapper.fromDtoToEntity(participantDTO).getIdUser());
        Participant p = participantRepository.save(existingParticipant);

        return participantModelMapper.fromEntityToDTO(p);
    }


    public ParticipantDTO delete(Long id) {

        Participant existingParticipant = participantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Participant id=%d does not exist", id)));
        participantRepository.deleteById(id);

        return participantModelMapper.fromEntityToDTO(existingParticipant);
    }
}
