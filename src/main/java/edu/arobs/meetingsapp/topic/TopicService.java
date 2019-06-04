package edu.arobs.meetingsapp.topic;

import edu.arobs.meetingsapp.event.EventDTO;
import edu.arobs.meetingsapp.event.EventDetails;
import edu.arobs.meetingsapp.user.User;
import edu.arobs.meetingsapp.user.UserRepository;
import edu.arobs.meetingsapp.vote.Vote;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final TopicModelMapper topicModelMapper;

    @Autowired
    public TopicService(TopicRepository topicRepository, UserRepository userRepository, ModelMapper modelMapper, TopicModelMapper topicModelMapper) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.topicModelMapper = topicModelMapper;
    }

    public TopicDTO createTopic(LinkedHashMap<String, Object> topic) {
        Integer userId = Integer.valueOf(topic.get("usersId").toString());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", userId)));
        TopicDTO topicDTO = new TopicDTO();
        Topic newTopic = new Topic();
        newTopic.setSumOfVotes(Integer.valueOf(topic.get("sumOfVotes").toString()));
        newTopic.setTopicContent(topic.get("topicContent").toString());
        newTopic.setTopicTitle(topic.get("topicTitle").toString());
        newTopic.setTopicType(Type.valueOf(topic.get("topicType").toString()));
        newTopic.setUser(user);
        newTopic.setUserPresenter(Boolean.valueOf(topic.get("isUserPresenter").toString()));
        List<Vote> usersVotes;
        usersVotes = (ArrayList<Vote>) topic.get("userVotes");
        newTopic.setUsersVotes(usersVotes);
        topicRepository.save(newTopic);
        modelMapper.map(newTopic, topicDTO);
        System.out.println(topicDTO);
        return topicDTO;
    }

    public List<TopicDTO> getTopics() {

        List<TopicDTO> topicDTOS = new ArrayList<>();
        TopicDTO topicDTO = new TopicDTO();
        for (Topic topic : topicRepository.findAll()) {
                modelMapper.map(topic, topicDTO);
                topicDTOS.add(topicDTO);
        }

        return topicDTOS;
    }
}
