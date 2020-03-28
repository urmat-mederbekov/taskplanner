package kg.attractor.taskplanner.repository;

import kg.attractor.taskplanner.model.Task;
import kg.attractor.taskplanner.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TaskRepo extends PagingAndSortingRepository<Task, String> {

    Optional<Task> findByIdAndUserEmail(String taskId, String email);
    Optional<Task> findByIdAndUser(String taskId, User user); // to test
    Slice<Task> findAllByUserEmail(String email, Pageable pageable);
    void deleteByIdAndUserEmail(String taskId, String email);
}
