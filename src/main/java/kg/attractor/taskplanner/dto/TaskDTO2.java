package kg.attractor.taskplanner.dto;

import kg.attractor.taskplanner.model.State;
import kg.attractor.taskplanner.model.Task;
import lombok.*;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Data
public class TaskDTO2 {

    public static TaskDTO2 from(Task task){
        return builder()
                .id(task.getId())
                .title(task.getTitle())
                .executionDateTime(task.getExecutionDateTime())
                .status(task.getStatus())
                .build();
    }

    private String id;
    private String title;
    private LocalDateTime executionDateTime;
    private State status;
}
