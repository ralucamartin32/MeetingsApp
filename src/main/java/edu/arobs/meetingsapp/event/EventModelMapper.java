package edu.arobs.meetingsapp.event;

import edu.arobs.meetingsapp.user.UserModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class EventModelMapper {
    @Autowired
    private final UserModelMapper userModelMapper ;

    public EventModelMapper(UserModelMapper userModelMapper) {
        this.userModelMapper = userModelMapper;
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

        return eventDTO;
    }

}