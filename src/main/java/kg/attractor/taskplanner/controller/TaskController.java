package kg.attractor.taskplanner.controller;

import kg.attractor.taskplanner.dto.TaskDTO;
import kg.attractor.taskplanner.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDTO addTask(@RequestBody TaskDTO taskData, Authentication authentication){
        return taskService.addTask(taskData, authentication);
    }

    @GetMapping("/{taskId}")
    public TaskDTO getTask(@PathVariable String taskId, Authentication authentication){
        return taskService.getTaskById(taskId, authentication);
    }

    @PutMapping("/task-state/{taskId}")
    public TaskDTO modifyTaskState(@PathVariable String taskId, Authentication authentication){
        return taskService.modifyTaskState(taskId, authentication);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable String taskId, Authentication authentication){
        if(taskService.deleteTaskById(taskId, authentication))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
