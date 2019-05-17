package edu.arobs.meetingsapp.proposal;

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
    public ProposalDTOForCreate create(@RequestParam(required = true) Integer userId, @RequestBody ProposalDTOForCreate proposalDTO) {
        return proposalService.create(userId, proposalDTO);
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

    @GetMapping("/getAll")
    @ResponseBody
    public List<ProposalDTO> getAll() {
        return proposalService.getAllProposals();
    }

    @GetMapping("/getAllByUserId")
    @ResponseBody
    public List<ProposalDTO> getByUserId(@RequestParam(required = true) Integer userId) {
        return proposalService.getProposalsForUser(userId);
    }

    @GetMapping("/getAllByYear")
    @ResponseBody
    public List<ProposalDTO> getProposalByYear(@RequestParam(required = true) Integer year) {
        return proposalService.getProposalsByYear(year);
    }

    @GetMapping("/getAllForUserByYear")
    public List<ProposalsFromUsersForYearDTO> getProposalsFromUserByYear(@RequestParam(required = true) Integer userId, @RequestParam(required = true) Integer year ){
        return proposalService.getProposalsFromUserForYear(userId, year);
    }
}
