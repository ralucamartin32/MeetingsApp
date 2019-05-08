package edu.arobs.meetingsapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @GetMapping("/getById")
    @ResponseBody
    public UserDTO getById(@RequestParam(required = true) Long id) {
        return userService.getById(id);
    }

    @PutMapping("/update")
    @ResponseBody
    public UserDTO update(@RequestParam(required = true) Long id, @RequestBody UserDTO userDTO){
        return userService.update(id, userDTO);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public UserDTO delete(@RequestParam(required = true) Long id ){
        return userService.delete(id);
    }
}
