package edu.arobs.meetingsapp.Feedback;

import edu.arobs.meetingsapp.event.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {

    List<Feedback> findByEvent(Event event);
}
