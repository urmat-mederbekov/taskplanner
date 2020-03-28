package kg.attractor.taskplanner.service;

import kg.attractor.taskplanner.dto.TaskDTO;
import kg.attractor.taskplanner.dto.TaskDTO2;
import kg.attractor.taskplanner.exception.ResourceNotFoundException;
import kg.attractor.taskplanner.model.Task;
import kg.attractor.taskplanner.model.User;
import kg.attractor.taskplanner.model.State;
import kg.attractor.taskplanner.repository.TaskRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskDTO getTaskById(String taskId, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return TaskDTO.from(taskRepo.findByIdAndUserEmail(taskId, user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + taskId + " not found")));
    }

    public Slice<TaskDTO2> getTasks(Authentication authentication, Pageable pageable){
        User user = (User) authentication.getPrincipal();
        Slice<Task> tasks = taskRepo.findAllByUserEmail(user.getEmail(), pageable);
        return tasks.map(TaskDTO2::from);
    }

    public TaskDTO modifyTaskState(String taskId, Authentication authentication){

        User user = (User) authentication.getPrincipal();
        Task task = taskRepo.findByIdAndUserEmail(taskId, user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + taskId + " not found"));
        task.setStatus(task.getStatus().nextState());

        taskRepo.save(task);
        return TaskDTO.from(task);
    }

    public TaskDTO addTask(TaskDTO taskData, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Task task = Task.builder()
                .description(taskData.getDescription())
                .executionDateTime(taskData.getExecutionDateTime())
                .title(taskData.getTitle())
                .id(UUID.randomUUID().toString())
                .status(State.NEW)
                .user(user)
                .build();

        taskRepo.save(task);
        return  TaskDTO.from(task);
    }

    public boolean deleteTaskById(String taskId, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        taskRepo.deleteByIdAndUserEmail(taskId, user.getEmail());
        return true;
    }
}