package edu.arobs.meetingsapp.event;

import edu.arobs.meetingsapp.Feedback.Feedback;
import edu.arobs.meetingsapp.Feedback.FeedbackDTO;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

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


    public EventDTO getById(Integer eventId) {
        Event eventExists = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException((String.format("Event id = %d doesn`t exist", eventId))));
        EventDetails eventDetails = eventDetailsRepository.findByEvent(eventExists);
        System.out.println("S a apelat getById");
        return eventModelMapper.fromEntityToDto(eventDetails);
    }


    public EventDTO subscribeAtEvent(Integer eventId, Integer token, List<Integer> attendanceIds) {

        System.out.println("S a apelat subscribeAtEvent");
        Event eventExists = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException((String.format("Event id = %d doesn`t exist", eventId))));
        User userExists = userRepository.findById(token)
                .orElseThrow(() -> new IllegalArgumentException((String.format("User id = %d doesn`t exist", Integer.valueOf(token)))));
        EventDTO eDTO = new EventDTO();
        // List<Integer> attendeesIds = new ArrayList<>();
        for (EventDetails e : eventDetailsRepository.findAll()) {
            if (e.getEvent().getId().equals(eventExists.getId())) {
                Attendees attendees = new Attendees(eventExists, userExists);
                attendees.setEvent(eventExists);
                attendees.setUser(userExists);
                attendees.setRegistrationDate(Timestamp.valueOf(LocalDateTime.now()));
                System.out.println("attendees " + attendees);
                attendeesRepository.save(attendees);
                eDTO = eventModelMapper.fromEntityToDto(e);
                //  attendeesIds.add(token);
                System.out.println("THE TOKEN in subscribeService is " + token);
                List<Attendees> attendeesList = attendeesRepository.findAllByEvent_Id(eventId);
                attendanceIds = attendeesList.stream().map(attendees1 -> attendees1.getUser().getId()).collect(Collectors.toList());
                // attendanceIds.add(token);
                eDTO.setAttendanceIds(attendanceIds);
                return eDTO;
            }
        }
        return eDTO;
    }

    public EventDTO unsubscribeAtEvent(Integer eventId, Integer userId, List<Integer> attendanceIds) {

        Event eventExists = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException((String.format("Event id = %d doesn`t exist", eventId))));
        User userExists = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException((String.format("User id = %d doesn`t exist", Integer.valueOf(userId)))));
        List<Attendees> attendeesList = attendeesRepository.findAllByEvent_Id(eventId);
        EventDTO eDTO = new EventDTO();
        EventDetails eventDetails = eventDetailsRepository.findByEventId(eventExists.getId());
        eDTO = eventModelMapper.fromEntityToDto(eventDetails);
        for(Attendees attendees : attendeesList){
            if(attendees.getUser().getId().equals(userId) && attendees.getEvent().getId().equals(eventId)){
                int index = attendeesList.indexOf(attendees);
                attendeesList.remove(index);
                attendeesRepository.delete(attendees);
                List<Attendees> attendeesList1 = attendeesRepository.findAllByEvent_Id(eventId);
                attendanceIds = attendeesList1.stream().map(attendees1 -> attendees1.getUser().getId()).collect(Collectors.toList());
                eDTO.setAttendanceIds(attendanceIds);
                return eDTO;
            }
        }
        return eDTO;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public EventDTO addFeedback(Integer eventId, ArrayList<LinkedHashMap<String, Object>> feedback) {

        System.out.println("Entered inside the addFeedback Service");


        Event eventExists = entityManager.find(Event.class, eventId);

        System.out.println("event exists" + eventExists);
        List<Feedback> feedbacks = feedbackRepository.findByEvent(eventExists);

        List<FeedbackDTO> feedbackDTOS = new ArrayList<>();
        FeedbackDTO newFeedback = new FeedbackDTO();
        for (Feedback feedback1 : feedbacks) {
            modelMapper.map(feedback1, newFeedback);
            feedbackDTOS.add(newFeedback);
        }

        LinkedHashMap<String, Object> feedback1 = new LinkedHashMap<>();
        EventDetails eventDetails = eventDetailsRepository.findByEvent(eventExists);
        EventDTO eventDTO1 = eventModelMapper.fromEntityToDto(eventDetails);
        Feedback feedback3 = new Feedback();
        for (LinkedHashMap<String, Object> feedback2 : feedback) {
            System.out.println("INTRU in for");

            feedback3.setClarity(feedback2.get("clarity").toString());
            feedback3.setOriginality(feedback2.get("originality").toString());
            feedback3.setComplexity(feedback2.get("complexity").toString());
            feedback3.setEngagement(feedback2.get("engagement").toString());
            feedback3.setCursive(feedback2.get("cursive").toString());

            feedback3.setEvent(eventExists);
            feedbackRepository.save(feedback3);



            FeedbackDTO feedbackDTO = new FeedbackDTO();
            modelMapper.map(feedback3, feedbackDTO);
            feedbackDTO.setUsersId(eventExists.getUser().getId());

            eventExists.addFeedback(feedback3);

            feedbacks.add(feedback3);

            feedbackDTOS.add(feedbackDTO);

            //eventDTO1.setFeedback(feedbacks);
            eventDTO1.setFeedback(feedbackDTOS);

          //  return eventDTO1;

        }


        return eventDTO1;
    }


}
