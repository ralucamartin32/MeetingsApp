package edu.arobs.meetingsapp.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendeesRepository extends JpaRepository<Attendees,AttendeesId> {

    Attendees findByEvent(Event event);
    List<Attendees> findAllByEvent_Id(Integer eventId);
}
