package edu.arobs.meetingsapp.meeting;

import edu.arobs.meetingsapp.user.User;
import edu.arobs.meetingsapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meetings")
public class MeetingController {
    private final MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping
    public Meeting create(@RequestBody Meeting meeting) {
        return meetingService.create(meeting);
    }

    @GetMapping("/getById")
    @ResponseBody
    public Meeting getById(@RequestParam(required = true) Long id) {
        return meetingService.getById(id);
    }

    @PutMapping("/update")
    @ResponseBody
    public Meeting update(@RequestParam(required = true) Long id, @RequestBody Meeting meeting){
        return meetingService.update(id, meeting);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Meeting delete(@RequestParam(required = true) Long id ){
        return meetingService.delete(id);
    }
}
