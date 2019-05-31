package edu.arobs.meetingsapp.topic;

import edu.arobs.meetingsapp.user.User;
import edu.arobs.meetingsapp.user.UserRepository;
import edu.arobs.meetingsapp.vote.Vote;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TopicService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public TopicService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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
        List<Vote> usersVotes = new ArrayList<>();
        usersVotes = (ArrayList<Vote>)  topic.get("userVotes");
        newTopic.setUsersVotes(usersVotes);
        modelMapper.map(newTopic, topicDTO);
        return topicDTO;
    }
}
