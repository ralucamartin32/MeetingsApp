package edu.arobs.meetingsapp.proposal;

import edu.arobs.meetingsapp.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proposals")
public class ProposalController {

    private final ProposalService proposalService;

    @Autowired
    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProposalDTO create(@RequestParam(required = true) Integer id, @RequestBody ProposalDTO proposalDTO) {
        return proposalService.create(id, proposalDTO);
    }

    @GetMapping("/getById")
    @ResponseBody
    public ProposalDTO getById(@RequestParam(required = true) Integer id) {
        return proposalService.getById(id);
    }


    @PutMapping("/update")
    @ResponseBody
    public ProposalDTO update(@RequestParam(required = true) Integer userId, @RequestParam(required = true) Integer proposalId, @RequestBody ProposalDTO proposalDTO) {
        return proposalService.update(userId, proposalId, proposalDTO);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public ProposalDTO delete(@RequestParam(required = true) Integer id) {
        return proposalService.delete(id);
    }

    @GetMapping("/getByUserId")
    @ResponseBody
    public List<Proposal> getByUserId(@RequestParam(required = true) Integer id) {
        return proposalService.getProposalsFromUser(id);
    }
}
