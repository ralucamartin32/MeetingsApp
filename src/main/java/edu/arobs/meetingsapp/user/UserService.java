package edu.arobs.meetingsapp.user;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserModelMapper userModelMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserModelMapper userModelMapper) {
        this.userRepository = userRepository;
        this.userModelMapper = userModelMapper;
    }

    public UserDTO create(UserDTO userDTO) {

        User user = userModelMapper.fromDtoToEntity(userDTO);
        user.setId(null);
        userRepository.save(user);
        LOGGER.info("Successfully created " + user);
        return userDTO;

    }

    public UserDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", id)));
        return userModelMapper.fromEntityToUserDTO(user);
    }


    public UserDTO update(Long id, UserDTO userDTO) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", id)));
        existingUser.setFirstName(userModelMapper.fromDtoToEntity(userDTO).getFirstName());
        existingUser.setLastName(userModelMapper.fromDtoToEntity(userDTO).getLastName());
        userRepository.save(existingUser);

        return userModelMapper.fromEntityToUserDTO(existingUser);
    }


    public UserDTO delete(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", id)));
        userRepository.deleteById(id);

        return userModelMapper.fromEntityToUserDTO(existingUser);
    }
}
