package kg.attractor.taskplanner.dto;

import kg.attractor.taskplanner.model.State;
import kg.attractor.taskplanner.model.Task;
import lombok.*;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Data
public class TaskDTO {
    public static TaskDTO from(Task task){
        return builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .executionDateTime(task.getExecutionDateTime())
                .status(task.getStatus())
                .build();
    }

    private String title;
    private String description;
    private LocalDateTime executionDateTime;
    private State status;
}
