package edu.arobs.meetingsapp.proposal;

import edu.arobs.meetingsapp.user.User;
import org.springframework.data.repository.CrudRepository;

public interface ProposalRepository extends CrudRepository<Proposal, Integer> {
    Proposal findByUser(User user);
}
