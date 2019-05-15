package edu.arobs.meetingsapp.meeting;

import edu.arobs.meetingsapp.Participant.ParticipantDTO;
import edu.arobs.meetingsapp.user.User;
import edu.arobs.meetingsapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetings")
public class MeetingController {
//    private final MeetingService meetingService;
//
//    @Autowired
//    public MeetingController(MeetingService meetingService) {
//        this.meetingService = meetingService;
//    }
//
//    @PostMapping
//    public MeetingDTO create(@RequestBody MeetingDTO meetingDTO) {
//        return meetingService.create(meetingDTO);
//    }
//
//    @PostMapping("/addParticipant")
//    public ParticipantDTO addParticipant(@RequestParam(required = true) Long idMeeting, @RequestParam(required = true) Long idUser) {
//        return meetingService.addParticipant(idMeeting, idUser);
//    }
//
//    @GetMapping("/getById")
//    @ResponseBody
//    public MeetingDTO getById(@RequestParam(required = true) Long id) {
//        return meetingService.getById(id);
//    }
//
//    @PutMapping("/update")
//    @ResponseBody
//    public MeetingDTO update(@RequestParam(required = true) Long id, @RequestBody MeetingDTO meetingDTO) {
//        return meetingService.update(id, meetingDTO);
//    }
//
//    @DeleteMapping("/delete")
//    @ResponseBody
//    public MeetingDTO delete(@RequestParam(required = true) Long id) {
//        return meetingService.delete(id);
//    }
//
//    @GetMapping("/getAll")
//    @ResponseBody
//    public List<MeetingDTO> getAll() {
//        return meetingService.getAll();
//    }
}
