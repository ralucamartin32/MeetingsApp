package edu.arobs.meetingsapp.event;

import org.springframework.data.repository.CrudRepository;

public interface AttendeesRepository extends CrudRepository<Attendees,AttendeesId> {

    Attendees findByEvent(Event event);
}
