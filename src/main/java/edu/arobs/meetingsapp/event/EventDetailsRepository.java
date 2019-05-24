package edu.arobs.meetingsapp.event;

import org.springframework.data.repository.CrudRepository;

public interface EventDetailsRepository extends CrudRepository<EventDetails, Integer> {
    EventDetails findByEvent(Event event);
}
