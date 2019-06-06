package edu.arobs.meetingsapp.topic;

import edu.arobs.meetingsapp.user.User;
import edu.arobs.meetingsapp.user.UserRepository;
import edu.arobs.meetingsapp.vote.Vote;
import edu.arobs.meetingsapp.vote.VoteDTO;
import edu.arobs.meetingsapp.vote.VoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;
    private final ModelMapper modelMapper;
    private final TopicModelMapper topicModelMapper;

    @Autowired
    public TopicService(TopicRepository topicRepository, UserRepository userRepository, VoteRepository voteRepository, ModelMapper modelMapper, TopicModelMapper topicModelMapper) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
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

        for (Topic topic : topicRepository.findAll()) {
            TopicDTO topicDTO = new TopicDTO();
            modelMapper.map(topic, topicDTO);
            topicDTOS.add(topicDTO);
        }

        return topicDTOS;
    }

    public TopicDTO getById(Integer topicId) {
        TopicDTO topicDTO = new TopicDTO();
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Topic id=%d does not exist", topicId)));
        modelMapper.map(topic, topicDTO);
        return topicDTO;
    }

    public TopicDTOForVote voteTopic(PostPatchDTO postPatchDTO, Integer topicId) {

        Integer vote = postPatchDTO.getSumOfVotes();
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Topic id=%d does not exist", topicId)));
        topic.setSumOfVotes(topic.getSumOfVotes() + vote);
        Vote newVote = new Vote();
        if (vote.equals(-1)) {
            newVote.setMinus(vote);
            newVote.setPlus(0);
        } else {
            newVote.setPlus(vote);
            newVote.setMinus(0);
        }
        newVote.setTopic(topic);
        topicRepository.save(topic);
        voteRepository.save(newVote);
        TopicDTOForVote topicDTOForVote = new TopicDTOForVote();
        modelMapper.map(topic, topicDTOForVote);
        VoteDTO voteDTO = new VoteDTO();
        List<VoteDTO> voteDTOS = new ArrayList<>();
        for (Vote v : voteRepository.findAll()) {

            modelMapper.map(v, voteDTO);


            voteDTOS.add(voteDTO);

        }
        topicDTOForVote.setUserVotes(voteDTOS);
        return topicDTOForVote;
    }
}
