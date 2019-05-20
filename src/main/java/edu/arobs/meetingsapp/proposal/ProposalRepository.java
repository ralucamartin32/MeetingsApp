package edu.arobs.meetingsapp.proposal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProposalRepository extends CrudRepository<Proposal, Integer> {

    @Query(value = "SELECT * FROM proposal WHERE  user_id = :userId", nativeQuery = true)
    List<Proposal> getProposalsForUser(@Param("userId") Integer userId) ;

    @Query(value = "SELECT * FROM proposal WHERE year(created_at) = :year", nativeQuery = true)
    List<Proposal> getProposalsByYear(@Param("year") Integer year);

    @Query(value = "SELECT user.full_name as fullName, proposal.title as title FROM user inner JOIN proposal ON user.id = proposal.user_id and (user.id = :userId) and (year(proposal.created_at) = :year) ", nativeQuery = true)
    List<ProposalsFromUsersForYearDTO> getProposalsFromUserForYear(@Param("userId") Integer userId, @Param("year") Integer year);
}