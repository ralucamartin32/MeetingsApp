package edu.arobs.meetingsapp.user;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserModelMapper userModelMapper;

    //    private final ModelMapper modelMapper;
//
    @Autowired
    public UserService(UserRepository userRepository, UserModelMapper userModelMapper) {
        this.userRepository = userRepository;
        this.userModelMapper = userModelMapper;


    }

    @Transactional
    public UserDTO create(UserDTO userDTO) {

        User user = userModelMapper.fromDtoToEntity(userDTO);
        user.setId(null);
        User user1 = userRepository.save(user);
        user1.setToken("usrABC" + user1.getId());
        User savedUser = userRepository.save(user1);
        LOGGER.info("Successfully created " + savedUser);
        UserDTO savedUserDto = new UserDTO();
        savedUserDto = userModelMapper.fromEntityToDTO(savedUser);
        return savedUserDto;
    }

    @Transactional
    public UserDTO getById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", id)));
        return userModelMapper.fromEntityToDTO(user);
    }


    @Transactional
    public UserDTO update(Integer id, UserDTO userDTO) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", id)));
        existingUser.setFullName(userModelMapper.fromDtoToEntity(userDTO).getFullName());
        existingUser.setEmail(userModelMapper.fromDtoToEntity(userDTO).getEmail());
        existingUser.setPassword(userModelMapper.fromDtoToEntity(userDTO).getPassword());
        existingUser.setPoints(userModelMapper.fromDtoToEntity(userDTO).getPoints());
        existingUser.setToken(userModelMapper.fromDtoToEntity(userDTO).getToken());
        userRepository.save(existingUser);

        return userModelMapper.fromEntityToDTO(existingUser);
    }


    @Transactional
    public UserDTO delete(Integer id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", id)));
        userRepository.deleteById(id);

        return userModelMapper.fromEntityToDTO(existingUser);
    }

    @Transactional
    public List<UserDTO> getAll() {

        List<UserDTO> userDTOS = new ArrayList<>();
        for (User e : userRepository.findAll()) {
            userDTOS.add(userModelMapper.fromEntityToDTO(e));
        }

        return userDTOS;

    }

    @Transactional
    public UserDTO login(String email, String password) {
        User usrE = userRepository.findByEmailAndPassword(email, password);
        UserDTO userDTO;
        userDTO = userModelMapper.fromEntityToDTO(usrE);
        return userDTO;
    }
}
