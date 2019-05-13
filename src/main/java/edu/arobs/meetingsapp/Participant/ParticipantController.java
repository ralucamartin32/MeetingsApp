package edu.arobs.meetingsapp.Participant;

import edu.arobs.meetingsapp.meeting.MeetingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.util.List;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

//    private final ParticipantService participantService;
//
//    @Autowired
//    public ParticipantController(ParticipantService participantService) {
//        this.participantService = participantService;
//    }
//
//    @PostMapping
//    public ParticipantDTO create(@RequestBody ParticipantDTO participantDTO) {
//        return participantService.create(participantDTO);
//    }
//
//    @GetMapping("/getById")
//    @ResponseBody
//    public ParticipantDTO getById(@RequestParam(required = true) Long id) {
//        return participantService.getById(id);
//    }
//
//    @PutMapping("/update")
//    @ResponseBody
//    public ParticipantDTO update(@RequestParam(required = true) Long id, @RequestBody ParticipantDTO participantDTO) {
//        return participantService.update(id, participantDTO);
//    }
//
//
//    @DeleteMapping("/delete")
//    @ResponseBody
//    public ParticipantDTO delete(@RequestParam(required = true) Long id) {
//        return participantService.delete(id);
//    }
//
//
//    @GetMapping("/getAll")
//    @ResponseBody
//    public List<ParticipantDTO> getAll(){
//        return participantService.getAll();
//    }
}
