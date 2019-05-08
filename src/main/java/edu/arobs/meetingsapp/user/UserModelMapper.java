package edu.arobs.meetingsapp.user;

import org.springframework.stereotype.Component;

@Component
public class UserModelMapper {

    public User fromDtoToEntity(UserDTO userDTO){

        String str = userDTO.getName();
        String[] splited = str.trim().split("\\s+");
        User user = new User();
       // user.setId(userDTO.getId());
        user.setFirstName(splited[0]);
        user.setLastName(splited[1]);
        return user;
    }

    public UserDTO fromEntityToUserDTO(User user) {

        UserDTO userDTO = new UserDTO();
      //  userDTO.setId(user.getId());
        userDTO.setName(user.getFirstName()+ " " + user.getLastName() );
        return userDTO;
    }

}
