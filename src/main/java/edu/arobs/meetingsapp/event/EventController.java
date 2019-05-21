package edu.arobs.meetingsapp.event;

import edu.arobs.meetingsapp.user.UserDTO;
import edu.arobs.meetingsapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EventDTO create(@RequestParam(required = true) Integer userId, @RequestBody EventDTO eventDTO) {
        return eventService.create(userId, eventDTO);
    }

}
