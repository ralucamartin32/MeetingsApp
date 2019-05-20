package edu.arobs.meetingsapp.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceSpecTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserModelMapper userModelMapper;

    @Test
    public void get_User_SuccesThenFailForNonexistent() {
        //1. Preparing the mocks
        User user = new User();
        user.setId(1);
        user.setFullName("Eduard Covaci");
        user.setEmail("eduard@yahoo.com");
        user.setPassword("eduardcovaci");
        user.setPoints(0);
        user.setToken("aaa1");

        UserDTO userDTO = new UserDTO();

        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setPoints(user.getPoints());
        userDTO.setToken(user.getToken());

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        when(userModelMapper.fromEntityToDTO(user))
                .thenReturn(userDTO);


        //2. Calling the tested method


        userDTO = userService.getById(1);


        //3. Verify succes

        assertThat(userDTO.getFullName()).isEqualTo("Eduard Covaci");
        assertThat(userDTO.getEmail()).isEqualTo("eduard@yahoo.com");
        assertThat(userDTO.getPassword()).isEqualTo("eduardcovaci");
        assertThat(userDTO.getPoints()).isEqualTo(0);
        assertThat(userDTO.getToken()).isEqualTo("aaa1");

        //4. Verify fail

        assertThrows(IllegalArgumentException.class,
                () -> userService.getById(100));
    }

    @TestConfiguration
    static class UserServiceContextConfig {

        @MockBean
        private UserRepository userRepository;

        @MockBean
        private UserModelMapper userModelMapper;

        @Bean
        public UserService userService() {
            return new UserService(userRepository, userModelMapper);
        }
    }
}
