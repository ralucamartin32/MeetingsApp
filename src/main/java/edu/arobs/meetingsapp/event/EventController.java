package edu.arobs.meetingsapp.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/pastEvents")
    @ResponseBody
    public List<EventDTO> getPastEvents() {
        return eventService.getPastEvents();
    }


    @GetMapping("/futureEvents")
    @ResponseBody
    public List<EventDTO> getFutureEvents() {
        return eventService.findFutureEvents();
    }

    @GetMapping("/{eventId}")
    @ResponseBody
    public EventDTO getById(@PathVariable Integer eventId) {
        return eventService.getById(eventId);
    }


    @PatchMapping("/subscribe/{eventId}/{userId}")
    @ResponseBody
    public EventDTO subscribeAtEvent(@PathVariable Integer eventId, @PathVariable Integer userId, @RequestBody Object o) {
        System.out.println("Token inside the subscribe Controller " + userId);
        LinkedHashMap<String, List<Integer>> attendList = (LinkedHashMap<String, List<Integer>>) o;
        if (attendList.containsKey("attendanceIds")) {
            return eventService.subscribeAtEvent(eventId, userId, attendList.get("attendanceIds"));
        }
        return eventService.subscribeAtEvent(eventId, userId, attendList.get("waitingListIds"));

    }

    @PatchMapping("/unsubscribe/{eventId}/{userId}")
    @ResponseBody
    public EventDTO unsubscribeAtEvent(@PathVariable Integer eventId, @PathVariable Integer userId, @RequestBody Object o) {
        System.out.println("Token inside the unsubscribe Controller " + userId);
        LinkedHashMap<String, List<Integer>> attendList = (LinkedHashMap<String, List<Integer>>) o;
        return eventService.unsubscribeAtEvent(eventId, userId, attendList.get("attendanceIds"));
    }


    @PatchMapping("/{eventId}")
    @ResponseBody
    public EventDTO addFeedback(@PathVariable Integer eventId, @RequestBody Object o) {
        System.out.println("Token inside the addFeedback Controller ");
        LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>> feedbacks = (LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>>) o;
        return eventService.addFeedback(eventId, feedbacks.get("feedback"));
    }

}
