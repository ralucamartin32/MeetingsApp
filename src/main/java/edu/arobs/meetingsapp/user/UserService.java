package edu.arobs.meetingsapp.user;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

//    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserService.class);
//
//    private final UserRepository userRepository;
//    private final UserModelMapper userModelMapper;
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    public UserService(UserRepository userRepository, UserModelMapper userModelMapper, ModelMapper modelMapper) {
//        this.userRepository = userRepository;
//        this.userModelMapper = userModelMapper;
//        this.modelMapper = modelMapper;
//
//    }
//
//    public UserDTO create(UserDTO userDTO) {
//        User user = new User();
//        modelMapper.map(userDTO, user);
//        user.setId(null);
//        userRepository.save(user);
//        LOGGER.info("Successfully created " + user);
//        return userDTO;
//    }
//
////    public UserDTO create(UserDTO userDTO) {
////
////        User user = userModelMapper.fromDtoToEntity(userDTO);
////        user.setId(null);
////        userRepository.save(user);
////        LOGGER.info("Successfully created " + user);
////        return userDTO;
////
////    }
//
//
//    public UserDTO getById(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", id)));
//        UserDTO userDTO = new UserDTO();
//        modelMapper.map(user, userDTO);
//        return userDTO;
//    }
//
////    public UserDTO getById(Long id) {
////        User user = userRepository.findById(id)
////                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", id)));
////        return userModelMapper.fromEntityToUserDTO(user);
////    }
//
//    public UserDTO update(Long id, UserDTO userDTO) {
//
//        User existingUser = userRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", id)));
//        modelMapper.map(userDTO, existingUser);
//        userRepository.save(existingUser);
//
//        return userModelMapper.fromEntityToDTO(existingUser);
//    }
//
//
////    public UserDTO update(Long id, UserDTO userDTO) {
////
////        User existingUser = userRepository.findById(id)
////                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", id)));
////        existingUser.setFirstName(userModelMapper.fromDtoToEntity(userDTO).getFirstName());
////        existingUser.setLastName(userModelMapper.fromDtoToEntity(userDTO).getLastName());
////        userRepository.save(existingUser);
////
////        return userModelMapper.fromEntityToDTO(existingUser);
////    }
//
//
//    public UserDTO delete(Long id) {
//        User existingUser = userRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", id)));
//        userRepository.deleteById(id);
//
//        UserDTO userDTO = new UserDTO();
//        modelMapper.map(existingUser, userDTO);
//        return userDTO;
//    }
//
////    public UserDTO delete(Long id) {
////        User existingUser = userRepository.findById(id)
////                .orElseThrow(() -> new IllegalArgumentException(String.format("User id=%d does not exist", id)));
////        userRepository.deleteById(id);
////
////        return userModelMapper.fromEntityToDTO(existingUser);
////    }
//
//    public List<UserDTO> getAll() {
//
//        List<UserDTO> userDTOS = new ArrayList<>();
//        for (User e : userRepository.findAll()) {
//            UserDTO userDTO = new UserDTO();
//            modelMapper.map(e,userDTO);
//            userDTOS.add(userDTO);
//        }
//
//        return userDTOS;
//
//    }
////    public List<UserDTO> getAll() {
////
////        List<UserDTO> userDTOS = new ArrayList<>();
////        for (User e : userRepository.findAll()) {
////            userDTOS.add(userModelMapper.fromEntityToDTO(e));
////        }
////
////        return userDTOS;
////
////    }
}
