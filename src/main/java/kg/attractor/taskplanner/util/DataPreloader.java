package kg.attractor.taskplanner.util;

import kg.attractor.taskplanner.model.State;
import kg.attractor.taskplanner.model.Task;
import kg.attractor.taskplanner.model.User;
import kg.attractor.taskplanner.repository.TaskRepo;
import kg.attractor.taskplanner.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
@AllArgsConstructor
public class DataPreloader {

    private final PasswordEncoder encoder;
    @Bean
    CommandLineRunner loadData(UserRepo userRepo, TaskRepo taskRepo){
        return args -> {
            userRepo.deleteAll();
            taskRepo.deleteAll();

            List<User> users = new ArrayList<>();
            users.add(User.builder().username("Mishon").email("mishon@gmail.com").password(encoder.encode("mishon")).build());
            users.add(User.builder().username("Rick").email("rick@gmail.com").password(encoder.encode("rick")).build());
            users.add(User.builder().username("Daryl").email("daryl@gmail.com").password(encoder.encode("daryl")).build());
            users.add(User.builder().username("Negan").email("negan@gmail.com").password(encoder.encode("negan")).build());

            List<Task> tasks = new ArrayList<>();
            tasks.add(Task.builder().title("Reading a book").executionDateTime(LocalDateTime.now().minusDays(3))
                    .description(Generator.makeDescription()).id(UUID.randomUUID().toString()).status(State.NEW).user(users.get(3)).build());
            tasks.add(Task.builder().title("Launching a business").executionDateTime(LocalDateTime.now().minusMonths(4))
                    .description(Generator.makeDescription()).id(UUID.randomUUID().toString()).status(State.EXECUTING).user(users.get(3)).build());
            tasks.add(Task.builder().title("Watching a TV").executionDateTime(LocalDateTime.now().minusDays(5))
                    .description(Generator.makeDescription()).id(UUID.randomUUID().toString()).status(State.COMPLETED).user(users.get(0)).build());
            tasks.add(Task.builder().title("Meeting with friends").executionDateTime(LocalDateTime.now().minusDays(15))
                    .description(Generator.makeDescription()).id(UUID.randomUUID().toString()).status(State.NEW).user(users.get(1)).build());

            users.stream()
                    .peek(System.out::println)
                    .forEach(userRepo::save);
            tasks.stream()
                    .peek(System.out::println)
                    .forEach(taskRepo::save);
        };
    }
}
