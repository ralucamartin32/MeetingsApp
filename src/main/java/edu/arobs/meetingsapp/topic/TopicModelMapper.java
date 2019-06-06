package edu.arobs.meetingsapp.topic;

import org.springframework.stereotype.Component;

@Component
public class TopicModelMapper {

    public TopicDTO fromEntityToDto(Topic topic) {

        TopicDTO topicDTO = new TopicDTO();
        return topicDTO;
    }
}
