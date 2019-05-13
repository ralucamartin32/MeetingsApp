package edu.arobs.meetingsapp.meeting;

import edu.arobs.meetingsapp.Participant.Participant;
import edu.arobs.meetingsapp.Participant.ParticipantDTO;
import edu.arobs.meetingsapp.Participant.ParticipantRepository;
import edu.arobs.meetingsapp.user.User;
import edu.arobs.meetingsapp.user.UserDTO;
import edu.arobs.meetingsapp.user.UserRepository;
import edu.arobs.meetingsapp.user.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final MeetingRepository meetingRepository;
    private final MeetingModelMapper meetingModelMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public MeetingService(ParticipantRepository participantRepository, UserRepository userRepository, MeetingRepository meetingRepository, MeetingModelMapper meetingModelMapper, ModelMapper modelMapper) {
        this.participantRepository = participantRepository;
        this.userRepository = userRepository;
        this.meetingRepository = meetingRepository;
        this.meetingModelMapper = meetingModelMapper;
        this.modelMapper = modelMapper;
    }

    public MeetingDTO create(MeetingDTO meetingDTO) {

        Meeting meeting = new Meeting();
        modelMapper.map(meetingDTO, meeting);
        meeting.setId(null);
        Meeting m = meetingRepository.save(meeting);
        LOGGER.info("The meeting : " + meeting + " was successfully created ");
        MeetingDTO m1 = new MeetingDTO();
        modelMapper.map(m, m1);
        return m1;

    }

    public ParticipantDTO addParticipant(Long idMeeting, Long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", idUser)));
        Meeting meeting = meetingRepository.findById(idMeeting)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Meeting id=%d does not exist", idMeeting)));
        Participant newParticipant = meeting.addParticipant(user);
        System.out.println(newParticipant);
        newParticipant.setId(null);
        System.out.println("CEVA");
        Participant p = participantRepository.save(newParticipant);
        System.out.println(p);
        ParticipantDTO participantDTO = new ParticipantDTO();
        modelMapper.map(p, participantDTO);
        return  participantDTO;
    }
//    public MeetingDTO create(MeetingDTO meetingDTO) {
//
//        Meeting meeting = meetingModelMapper.fromDtoToEntity(meetingDTO);
//        meeting.setId(null);
//        Meeting m = meetingRepository.save(meeting);
//        LOGGER.info("The meeting : " + meeting + " was successfully created ");
//        return meetingModelMapper.fromEntityToDTO(m);
//
//    }

    public MeetingDTO getById(Long id) {

        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Meeting id=%d does not exist", id)));

        MeetingDTO meetingDTO = new MeetingDTO();
        modelMapper.map(meeting, meetingDTO);
        return meetingDTO;
    }

//    public MeetingDTO getById(Long id) {
//
//        return meetingModelMapper.fromEntityToDTO(meetingRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException(String.format("Meeting id=%d does not exist", id))));
//    }

    public MeetingDTO update(Long id, MeetingDTO meetingDTO) {

        Meeting existingMeeting = meetingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Meeting id=%d does not exist", id)));
        modelMapper.map(meetingDTO, existingMeeting);
        Meeting m = meetingRepository.save(existingMeeting);
        MeetingDTO meetingDTO1 = new MeetingDTO();
        modelMapper.map(m, meetingDTO1);
        return meetingDTO1;
    }

//    public MeetingDTO update(Long id, MeetingDTO meetingDTO) {
//
//        Meeting existingMeeting = meetingRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException(String.format("Meeting id=%d does not exist", id)));
//        existingMeeting.setDate(meetingModelMapper.fromDtoToEntity(meetingDTO).getDate());
//        existingMeeting.setTitle(meetingModelMapper.fromDtoToEntity(meetingDTO).getTitle());
//        existingMeeting.setLocation(meetingModelMapper.fromDtoToEntity(meetingDTO).getLocation());
//
//        Meeting m = meetingRepository.save(existingMeeting);
//        return meetingModelMapper.fromEntityToDTO(m);
//    }

    public MeetingDTO delete(Long id) {

        Meeting existingMeeting = meetingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Meeting id=%d does not exist", id)));
        meetingRepository.deleteById(id);
        MeetingDTO meetingDTO = new MeetingDTO();
        modelMapper.map(existingMeeting, meetingDTO);
        return meetingDTO;
    }

//    public MeetingDTO delete(Long id) {
//
//        Meeting existingMeeting = meetingRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException(String.format("Meeting id=%d does not exist", id)));
//        meetingRepository.deleteById(id);
//
//        return meetingModelMapper.fromEntityToDTO(existingMeeting);
//    }

    public List<MeetingDTO> getAll() {

        List<MeetingDTO> meetingDTOS = new ArrayList<>();
        for (Meeting e : meetingRepository.findAll()) {
            MeetingDTO meetingDTO = new MeetingDTO();
            modelMapper.map(e, meetingDTO);
            meetingDTOS.add(meetingDTO);
        }

        return meetingDTOS;

    }
//    public List<MeetingDTO> getAll() {
//
//        List<MeetingDTO> meetingDTOS = new ArrayList<>();
//        for (Meeting e : meetingRepository.findAll()) {
//            meetingDTOS.add(meetingModelMapper.fromEntityToDTO(e));
//        }
//
//        return meetingDTOS;
//
//    }

}
