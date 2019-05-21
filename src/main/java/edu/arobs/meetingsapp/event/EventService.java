package edu.arobs.meetingsapp.event;

import edu.arobs.meetingsapp.user.User;
import edu.arobs.meetingsapp.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Service
public class EventService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EventService.class);
    private final EventRepository eventRepository;
    private final EventDetailsRepository eventDetailsRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EventService(EventRepository eventRepository, EventDetailsRepository eventDetailsRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.eventDetailsRepository = eventDetailsRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public EventDTO create(Integer userId, EventDTO eventDTO) {

        Event event = new Event();
        EventDetails eventDetails = new EventDetails();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", userId)));
        System.out.println(user);
        //Assert.notNull(user, "user cannot be null");
        event.setUser(user);

        modelMapper.map(eventDTO, eventDetails);
        eventDetails.setDate(Timestamp.valueOf(eventDTO.getDate()));
        eventDetails.setTime(Timestamp.valueOf(eventDTO.getTime()));
        System.out.println(eventDetails);
        event.setId(null);
        eventDetails.setId(null);
        eventRepository.save(event);
        eventDetailsRepository.save(eventDetails);
        LOGGER.info("Event successfully created " + event);
        EventDTO eventDTO1 = new EventDTO();
        modelMapper.map(eventDetails, eventDTO1);
        return eventDTO1;

    }
}
