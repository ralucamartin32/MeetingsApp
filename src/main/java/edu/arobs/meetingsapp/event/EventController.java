package edu.arobs.meetingsapp.event;

import edu.arobs.meetingsapp.Feedback.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

//    @GetMapping
//    @ResponseBody
//    public List<EventDTO> AllEvents(@RequestParam(required = true) String _expand, @RequestHeader("Referer") String header,
//                                    @CookieValue("token") String token, HttpServletRequest httpRequest) {
//        System.out.println("TOKEN from controller is " + token);
//        if (header.equals("http://localhost:3001/futureEvents")) {
//            return eventService.getAll();
//        } else if (header.equals("http://localhost:3000/pastEvents")) {
//            return eventService.getPastEvents();
//        }
//        return eventService.getAll();
//
//    }

    @GetMapping("/pastEvents")
    @ResponseBody
    public List<EventDTO> getPastEvents(){
        return eventService.getPastEvents();
    }


    @GetMapping("/futureEvents")
    @ResponseBody
    public List<EventDTO> getFutureEvents(){
        return eventService.findFutureEvents();
    }

    @GetMapping("/{eventId}")
    @ResponseBody
    public EventDTO getById(@PathVariable Integer eventId) {
        return eventService.getById(eventId);
    }

//    @PatchMapping("/{eventId}")
//    @ResponseBody
//    public EventDTO subscribeAtEvent(@PathVariable Integer eventId, @CookieValue("token") String token) {
//        System.out.println("Token inside the subscribe Controller " + token);
//        return eventService.subscribeAtEvent(eventId, token);
//    }

    @PatchMapping("/subscribe/{eventId}/{userId}")
    @ResponseBody
    public EventDTO subscribeAtEvent(@PathVariable Integer eventId, @PathVariable Integer userId, @RequestBody Object o) {
        System.out.println("Token inside the subscribe Controller " + userId);
        LinkedHashMap<String,List<Integer>> attendList =  (LinkedHashMap<String,List<Integer>>) o;
        return eventService.subscribeAtEvent(eventId, userId, attendList.get("attendanceIds"));
    }

    @PatchMapping("/unsubscribe/{eventId}/{userId}")
    @ResponseBody
    public EventDTO unsubscribeAtEvent(@PathVariable Integer eventId, @PathVariable Integer userId, @RequestBody Object o) {
        System.out.println("Token inside the unsubscribe Controller " + userId);
        LinkedHashMap<String,List<Integer>> attendList =  (LinkedHashMap<String,List<Integer>>) o;
        return eventService.unsubscribeAtEvent(eventId, userId, attendList.get("attendanceIds"));
    }


    @PatchMapping("/{eventId}")
    @ResponseBody
    public EventDTO addFeedback(@PathVariable Integer eventId, @RequestBody Object o ) {
        System.out.println("Token inside the addFeedback Controller " );
        LinkedHashMap<String, ArrayList<LinkedHashMap<String,Object>>> feedbacks = (LinkedHashMap<String, ArrayList<LinkedHashMap<String,Object>>>) o;
        return eventService.addFeedback(eventId, feedbacks.get("feedback"));
    }

}
