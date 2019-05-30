package edu.arobs.meetingsapp.event;

import edu.arobs.meetingsapp.Feedback.Feedback;
import edu.arobs.meetingsapp.Feedback.FeedbackDTO;
import edu.arobs.meetingsapp.Feedback.FeedbackRepository;
import edu.arobs.meetingsapp.user.UserModelMapper;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventModelMapper {
    @Autowired
    private final UserModelMapper userModelMapper ;

    @Autowired
    private final AttendeesRepository attendeesRepository;

    @Autowired
    private final FeedbackRepository feedbackRepository;
    @Autowired
    private final ModelMapper modelMapper;
    public EventModelMapper(UserModelMapper userModelMapper, AttendeesRepository attendeesRepository, FeedbackRepository feedbackRepository, ModelMapper modelMapper)
    {
        this.userModelMapper = userModelMapper;
        this.attendeesRepository = attendeesRepository;
        this.feedbackRepository = feedbackRepository;
        this.modelMapper = modelMapper;
    }

    public EventDetails fromDtoToEntity(EventDTO eventDTO) {


        EventDetails eventDetails = new EventDetails();

        eventDetails.setDate(eventDTO.getDate());
        eventDetails.setName(eventDTO.getName());
        eventDetails.setTime(eventDTO.getTime());
        eventDetails.setType(eventDTO.getType());
        eventDetails.setLanguage(eventDTO.getLang());
        eventDetails.setDuration(eventDTO.getDuration());
        eventDetails.setDifficulty(eventDTO.getDifficulty());
        eventDetails.setMaxPersons(eventDTO.getMaxPeople());
        eventDetails.setDescription(eventDTO.getDescription());


        return eventDetails;
    }

    public EventDTO fromEntityToDto(EventDetails eventDetails) {

        EventDTO eventDTO = new EventDTO();
        LocalDateTime localDateTime = LocalDateTime.of(eventDetails.getDate().toLocalDate(),eventDetails.getTime().toLocalTime());

        ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
        long epoch = localDateTime.atZone(zoneId).toEpochSecond();

       List<Attendees> list = attendeesRepository.findAllByEvent_Id(eventDetails.getId());

//        System.out.println("LISTA " + list);
        List<Integer> intList = list.stream().map(attendees -> attendees.getUser().getId()).collect(Collectors.toList());

        eventDTO.setId(eventDetails.getEvent().getId());
        eventDTO.setUsers(userModelMapper.fromEntityToDTO(eventDetails.getEvent().getUser()));
        eventDTO.setUsersId(eventDetails.getEvent().getUser().getId());
        eventDTO.setTimestamp(epoch);
        eventDTO.setDate(eventDetails.getDate());
        eventDTO.setName(eventDetails.getName());
        eventDTO.setTime(eventDetails.getTime());
        eventDTO.setType(eventDetails.getType());
        eventDTO.setLang(eventDetails.getLanguage());
        eventDTO.setDuration(eventDetails.getDuration());
        eventDTO.setDifficulty(eventDetails.getDifficulty());
        eventDTO.setMaxPeople(eventDetails.getMaxPersons());
        eventDTO.setDescription(eventDetails.getDescription());
        eventDTO.setAttendanceIds(intList);

        List<Feedback> feedbackList = feedbackRepository.findByEvent(eventDetails.getEvent());
        List<FeedbackDTO> feedbackDTOList = new ArrayList<>();
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        for(Feedback feedback : feedbackList){
            modelMapper.map(feedback, feedbackDTO);
            feedbackDTOList.add(feedbackDTO);
        }

        eventDTO.setFeedback(feedbackDTOList);






        return eventDTO;
    }

}
