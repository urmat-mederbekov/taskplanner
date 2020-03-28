package kg.attractor.taskplanner.controller;

import kg.attractor.taskplanner.dto.UserDTO;
import kg.attractor.taskplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
@AllArgsConstructor
public class MainController {
    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO registerUser(@RequestBody UserDTO userData){
        return userService.registerUser(userData);
    }
}
