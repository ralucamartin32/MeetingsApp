package edu.arobs.meetingsapp.user;

import org.springframework.stereotype.Component;

@Component
public class UserModelMapper {

    public User fromDtoToEntity(UserDTO userDTO) {

        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPoints(userDTO.getPoints());
        user.setToken(userDTO.getToken());
        return user;
    }

    public UserDTO fromEntityToDTO(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setPoints(user.getPoints());
        userDTO.setToken(user.getToken());
        return userDTO;
    }

}
