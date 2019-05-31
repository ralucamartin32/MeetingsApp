package edu.arobs.meetingsapp.topic;

import edu.arobs.meetingsapp.proposal.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/proposedTopics")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {

        this.topicService = topicService;
    }

    @PostMapping
    @ResponseBody
    public TopicDTO createTopic(@RequestBody Object o) {
        System.out.println("topic ");
        LinkedHashMap<String, Object> topic = (LinkedHashMap<String, Object>) o;
        return topicService.createTopic(topic);
    }
}
