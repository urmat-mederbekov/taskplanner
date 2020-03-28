package kg.attractor.taskplanner.repository;

import kg.attractor.taskplanner.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepo extends PagingAndSortingRepository<User, String> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
