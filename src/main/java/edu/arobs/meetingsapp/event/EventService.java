package edu.arobs.meetingsapp.event;

import edu.arobs.meetingsapp.Feedback.Feedback;
import edu.arobs.meetingsapp.Feedback.FeedbackRepository;
import edu.arobs.meetingsapp.user.User;
import edu.arobs.meetingsapp.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EventService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EventService.class);
    private final EventRepository eventRepository;
    private final EventDetailsRepository eventDetailsRepository;
    private final UserRepository userRepository;
    private final AttendeesRepository attendeesRepository;
    private final ModelMapper modelMapper;
    private final EventModelMapper eventModelMapper;
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public EventService(EventRepository eventRepository, EventDetailsRepository eventDetailsRepository, UserRepository userRepository, AttendeesRepository attendeesRepository, ModelMapper modelMapper, EventModelMapper eventModelMapper, FeedbackRepository feedbackRepository) {
        this.eventRepository = eventRepository;
        this.eventDetailsRepository = eventDetailsRepository;
        this.userRepository = userRepository;
        this.attendeesRepository = attendeesRepository;
        this.modelMapper = modelMapper;
        this.eventModelMapper = eventModelMapper;
        this.feedbackRepository = feedbackRepository;
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

    @Transactional
    public List<EventDTO> findFutureEvents() {
        System.out.println("FUTURE SERVICE");
        List<EventDTO> futureEvents = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();

        EventDTO eDTO;
        for (EventDetails e : eventDetailsRepository.findAll()) {
            LocalDateTime ldt = LocalDateTime.of(e.getDate().toLocalDate(), e.getTime().toLocalTime());
            if (ldt.isAfter(localDateTime)) {
                eDTO = eventModelMapper.fromEntityToDto(e);
                futureEvents.add(eDTO);
            }
        }

        return futureEvents;

    }
//
//    public EventDTO subscribeAtEvent(Integer eventId, String token) {
//
//        System.out.println("S a apelat subscribeAtEvent");
//        List<EventDTO> eventDTOS = new ArrayList<>();
//        Event eventExists = eventRepository.findById(eventId)
//                .orElseThrow(() -> new IllegalArgumentException((String.format("Event id = %d doesn`t exist", eventId))));
//        User userExists = userRepository.findById(Integer.valueOf(token.substring(6)))
//                .orElseThrow(() -> new IllegalArgumentException((String.format("Event id = %d doesn`t exist", Integer.valueOf(token.substring(6))))));
//        EventDTO eDTO = new EventDTO();
//        List<Integer> attendeesIds = new ArrayList<>();
//        for (EventDetails e : eventDetailsRepository.findAll()) {
//            if (e.getEvent().getId().equals(eventExists.getId())) {
//                Attendees attendees = new Attendees(eventExists, userExists);
//                attendees.setEvent(eventExists);
//                attendees.setUser(userExists);
//                attendees.setRegistrationDate(Timestamp.valueOf(LocalDateTime.now()));
//                System.out.println("attendees " + attendees);
//                attendeesRepository.save(attendees);
//                eDTO = eventModelMapper.fromEntityToDto(e);
//                attendeesIds.add(Integer.valueOf(token.substring(6)));
//                System.out.println("THE TOKEN in subscribeService is " + token.substring(6));
//                eDTO.setAttendanceIds(attendeesIds);
//            }
//        }
//
//        return eDTO;
//    }



    @Transactional
    public EventDTO addParticipant(Integer eventId, String token) {

        System.out.println("S a apelat subscribeAtEvent");
        Integer userId = Integer.valueOf(token.substring(6));
        System.out.println("S a apelat subscribeAtEvent" + userId);

        EventDetails eventdetailExists = eventDetailsRepository.findByEventId(eventId);
        Optional<User> user = userRepository.findById(userId);
        Optional<Event> event;
        if (eventdetailExists != null && user.isPresent()) {

            event = eventRepository.findById(eventId);
            if (event.isPresent()) {

                Attendees attendees = new Attendees(event.get(), user.get());

                attendees.setUser(user.get());
                attendees.setEvent(event.get());

                entityManager.persist(attendees);
//                user.get().getListOfAttendance().add(eventAttendanc);
//
//                event.get().getAttendancSetId().add(eventAttendanc);

            }

        }
        return eventModelMapper.fromEntityToDto(eventdetailExists);

    }


    public EventDTO getById(Integer eventId) {
        Event eventExists = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException((String.format("Event id = %d doesn`t exist", eventId))));
        EventDetails eventDetails = eventDetailsRepository.findByEvent(eventExists);
        System.out.println("S a apelat getById");
        return eventModelMapper.fromEntityToDto(eventDetails);
    }


//    public List<EventDTO> findPastEvents(){
//
//    }
//
//    public List<EventDTO> findMyEvents(){
//
//    }





    public EventDTO subscribeAtEvent(Integer eventId, Integer token) {

        System.out.println("S a apelat subscribeAtEvent");
        List<EventDTO> eventDTOS = new ArrayList<>();
        Event eventExists = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException((String.format("Event id = %d doesn`t exist", eventId))));
        User userExists = userRepository.findById(token)
                .orElseThrow(() -> new IllegalArgumentException((String.format("Event id = %d doesn`t exist", Integer.valueOf(token)))));
        EventDTO eDTO = new EventDTO();
        List<Integer> attendeesIds = new ArrayList<>();
        for (EventDetails e : eventDetailsRepository.findAll()) {
            if (e.getEvent().getId().equals(eventExists.getId())) {
                Attendees attendees = new Attendees(eventExists, userExists);
                attendees.setEvent(eventExists);
                attendees.setUser(userExists);
                attendees.setRegistrationDate(Timestamp.valueOf(LocalDateTime.now()));
                System.out.println("attendees " + attendees);
                attendeesRepository.save(attendees);
                eDTO = eventModelMapper.fromEntityToDto(e);
                attendeesIds.add(token);
                System.out.println("THE TOKEN in subscribeService is " + token);
                eDTO.setAttendanceIds(attendeesIds);
            }
        }

        return eDTO;
    }
    @PersistenceContext
    private EntityManager entityManager;

    public EventDTO addFeedback(Integer eventId,  ArrayList<LinkedHashMap<String, Object>> feedback) {

        System.out.println("Entered inside the addFeedback Service");

//        Event eventExists = eventRepository.findById(eventId)
//                .orElseThrow(() -> new IllegalArgumentException((String.format("Event id = %d doesn`t exist", eventId))));
        Event eventExists = entityManager.find( Event.class, eventId );

        System.out.println("event exists" + eventExists);
        List<Feedback> feedbacks = feedbackRepository.findByEvent(eventExists);
        LinkedHashMap<String, Object> feedback1 = new LinkedHashMap<>();
        EventDetails eventDetails = eventDetailsRepository.findByEvent(eventExists);
        EventDTO eventDTO1 = eventModelMapper.fromEntityToDto(eventDetails);
        for(LinkedHashMap<String, Object>  feedback2 : feedback){
            System.out.println("INTRU in for");
            Feedback feedback3 = new Feedback();
            feedback3.setClarity(feedback2.get("clarity").toString());
            feedback3.setOriginality(feedback2.get("originality").toString());
            feedback3.setComplexity(feedback2.get("complexity").toString());
            feedback3.setEngagement(feedback2.get("engagement").toString());
            feedback3.setCursive(feedback2.get("cursive").toString());

            feedback3.setEvent(eventExists);
            feedbackRepository.save(feedback3);
            eventExists.addFeedback(feedback3);

            feedbacks.add(feedback3);
            eventDTO1.setFeedback(feedbacks);

        }



        return eventDTO1;
    }
}
