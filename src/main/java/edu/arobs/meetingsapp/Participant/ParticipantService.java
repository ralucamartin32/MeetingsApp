package edu.arobs.meetingsapp.Participant;

import edu.arobs.meetingsapp.meeting.Meeting;
import edu.arobs.meetingsapp.meeting.MeetingDTO;
import edu.arobs.meetingsapp.user.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantService {

//    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserService.class);
//
//    private final ParticipantRepository participantRepository;
//    private final ParticipantModelMapper participantModelMapper;
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    public ParticipantService(ParticipantRepository participantRepository, ParticipantModelMapper participantModelMapper, ModelMapper modelMapper) {
//        this.participantRepository = participantRepository;
//        this.participantModelMapper = participantModelMapper;
//        this.modelMapper = modelMapper;
//    }
////
//
////    public ParticipantDTO create(ParticipantDTO participantDTO) {
////
////
////        Participant participant = new Participant();
////        modelMapper.map(participantDTO, participant);
////
////        participant.setId(null);
////        Participant p = participantRepository.save(participant);
////        LOGGER.info("Successfully created " + participant);
////        return participantDTO;
////    }
////
//    public ParticipantDTO create(ParticipantDTO participantDTO) {
//
//
//        Participant participant = participantModelMapper.fromDtoToEntity(participantDTO);
//        participant.setId(null);
//        Participant p =participantRepository.save(participant);
//        LOGGER.info("Successfully created " + participant);
//        return participantModelMapper.fromEntityToDTO(p);
//    }
//
//    public ParticipantDTO getById(Long id) {
//        Participant participant = participantRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException(String.format("Participant id=%d does not exist", id)));
//        ParticipantDTO participantDTO = new ParticipantDTO();
//        modelMapper.map(participant, participantDTO);
//        return participantDTO;
//    }
//
//
////    public ParticipantDTO getById(Long id) {
////        Participant participant = participantRepository.findById(id)
////                .orElseThrow(() -> new IllegalArgumentException(String.format("Participant id=%d does not exist", id)));
////        return participantModelMapper.fromEntityToDTO(participant);
////    }
//
//
//    public ParticipantDTO update(Long id, ParticipantDTO participantDTO) {
//
//        Participant existingParticipant = participantRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException(String.format("Participant id=%d does not exist", id)));
//        modelMapper.map(participantDTO, existingParticipant);
//        Participant p = participantRepository.save(existingParticipant);
//        ParticipantDTO participantDTO1 = new ParticipantDTO();
//        modelMapper.map(p, participantDTO1);
//        return participantDTO1;
//    }
//
////    public ParticipantDTO update(Long id, ParticipantDTO participantDTO) {
////
////        Participant existingParticipant = participantRepository.findById(id)
////                .orElseThrow(() -> new IllegalArgumentException(String.format("Participant id=%d does not exist", id)));
////        existingParticipant.setIdMeeting(participantModelMapper.fromDtoToEntity(participantDTO).getIdMeeting());
////        existingParticipant.setIdUser(participantModelMapper.fromDtoToEntity(participantDTO).getIdUser());
////        Participant p = participantRepository.save(existingParticipant);
////
////        return participantModelMapper.fromEntityToDTO(p);
////    }
//
//
//    public ParticipantDTO delete(Long id) {
//
//        Participant existingParticipant = participantRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException(String.format("Participant id=%d does not exist", id)));
//        participantRepository.deleteById(id);
//
//        return participantModelMapper.fromEntityToDTO(existingParticipant);
//    }
//
//    public List<ParticipantDTO> getAll() {
//
//        List<ParticipantDTO> participantDTOS = new ArrayList<>();
//        for (Participant e : participantRepository.findAll()) {
//            participantDTOS.add(participantModelMapper.fromEntityToDTO(e));
//        }
//
//        return participantDTOS;
//
//    }

}
