package edu.arobs.meetingsapp.event;

import edu.arobs.meetingsapp.user.UserDTO;
import edu.arobs.meetingsapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

//    @GetMapping
//    @ResponseBody
//    public List<EventDTO> AllEvents(@RequestParam(required = true) String _expand){
//        System.out.println("all events");
//
//        return eventService.getAll();
//
//    }

    @GetMapping
    @ResponseBody
    public List<EventDTO> AllEvents(@RequestParam(required = true) String _expand, @RequestHeader("Referer") String header){
        System.out.println("all events");
        if(header.equals("http://localhost:3001/futureEvents")){
            return eventService.getAll();
        }
        else if(header.equals("http://localhost:3000/pastEvents")){
            return eventService.getPastEvents();
        }
        return eventService.getAll();

    }

}
