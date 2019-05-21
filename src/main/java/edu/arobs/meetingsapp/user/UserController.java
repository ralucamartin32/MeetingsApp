package edu.arobs.meetingsapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.basic.BasicTreeUI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @GetMapping("/getById")
    @ResponseBody
    public UserDTO getById(@RequestParam(required = true) Integer id) {
        return userService.getById(id);
    }

    @PutMapping("/update")
    @ResponseBody
    public UserDTO update(@RequestParam(required = true) Integer id, @RequestBody UserDTO userDTO) {
        return userService.update(id, userDTO);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public UserDTO delete(@RequestParam(required = true) Integer id) {
        return userService.delete(id);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<UserDTO> getAll() {
        return userService.getAll();
    }
}
