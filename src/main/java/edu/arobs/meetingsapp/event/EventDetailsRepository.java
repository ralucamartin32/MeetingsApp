package edu.arobs.meetingsapp.event;

import edu.arobs.meetingsapp.proposal.Proposal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

public interface EventDetailsRepository extends CrudRepository<EventDetails, Integer> {
    EventDetails findByEvent(Event event);
    EventDetails findByEventId(Integer eventId);


    @Query(value = "SELECT * FROM event_details WHERE  date >= :date", nativeQuery = true)
    List<EventDetails> findFutureEventDetails(@Param("date") Date date) ;
}
