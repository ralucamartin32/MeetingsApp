package edu.arobs.meetingsapp.event;

import edu.arobs.meetingsapp.user.User;
import edu.arobs.meetingsapp.user.UserDTO;
import edu.arobs.meetingsapp.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EventService.class);
    private final EventRepository eventRepository;
    private final EventDetailsRepository eventDetailsRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final EventModelMapper eventModelMapper;

    @Autowired
    public EventService(EventRepository eventRepository, EventDetailsRepository eventDetailsRepository, UserRepository userRepository, ModelMapper modelMapper, EventModelMapper eventModelMapper) {
        this.eventRepository = eventRepository;
        this.eventDetailsRepository = eventDetailsRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.eventModelMapper = eventModelMapper;
    }

    @Transactional
    public EventDTO create(Integer userId, EventDTO eventDTO) {

        Event event = new Event();
        EventDetails eventDetails;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", userId)));


        event.setUser(user);

        eventDetails = eventModelMapper.fromDtoToEntity(eventDTO);
        eventDetails.setEvent(event);

        event.setId(null);
        eventDetails.setId(null);
        eventRepository.save(event);
        eventDetailsRepository.save(eventDetails);
        LOGGER.info("Event successfully created " + event);
        EventDTO eventDTO1 = new EventDTO();
        EventDTO eventDTO2;
        eventDTO2 = eventModelMapper.fromEntityToDto(eventDetails);
        return eventDTO2;


    }

    @Transactional
    public List<EventDTO> getAll() {

        List<EventDTO> eventDTOS = new ArrayList<>();
        EventDTO eDTO = new EventDTO();
        for (EventDetails e : eventDetailsRepository.findAll()) {
            eDTO = eventModelMapper.fromEntityToDto(e);
            eventDTOS.add(eDTO);
        }

        return eventDTOS;
    }

    @Transactional
    public List<EventDTO> getPastEvents() {

        List<EventDTO> eventDTOS = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        EventDTO eDTO;
        for (EventDetails e : eventDetailsRepository.findAll()) {
            LocalDateTime ldt = LocalDateTime.of(e.getDate().toLocalDate(), e.getTime().toLocalTime());
            if (localDateTime.isAfter(ldt)) {
                eDTO = eventModelMapper.fromEntityToDto(e);
                eventDTOS.add(eDTO);
            }
        }

        return eventDTOS;
    }
}
