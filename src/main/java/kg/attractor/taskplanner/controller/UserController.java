package kg.attractor.taskplanner.controller;

import kg.attractor.taskplanner.annotations.ApiPageable;
import kg.attractor.taskplanner.dto.TaskDTO2;
import kg.attractor.taskplanner.dto.UserDTO;
import kg.attractor.taskplanner.service.TaskService;
import kg.attractor.taskplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final TaskService taskService;

    @GetMapping("/user")
    public UserDTO getUser(Authentication authentication){
        return userService.getUser(authentication);
    }

    @ApiPageable
    @GetMapping("/user/tasks")
    public Slice<TaskDTO2> getTasks(Authentication authentication, @ApiIgnore Pageable pageable){
        return taskService.getTasks(authentication, pageable);
    }

    @DeleteMapping("/user")
    public ResponseEntity<Void> deleteUser(Authentication authentication){
        if(userService.deleteUser(authentication))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
