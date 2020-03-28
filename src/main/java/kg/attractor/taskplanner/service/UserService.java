package kg.attractor.taskplanner.service;

import kg.attractor.taskplanner.dto.UserDTO;
import kg.attractor.taskplanner.exception.ResourceNotFoundException;
import kg.attractor.taskplanner.model.User;
import kg.attractor.taskplanner.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    public UserDTO getUser(Authentication authentication){

        String username = authentication.getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(("User with username " + username + " not found")));
        return UserDTO.from(user);
    }

    public UserDTO registerUser(UserDTO userData){

        User user = User.builder()
                .username(userData.getUsername())
                .email(userData.getEmail())
                .password(encoder.encode(userData.getPassword()))
                .build();

        userRepo.save(user);
        return UserDTO.from(user);
    }

    public boolean deleteUser(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        userRepo.deleteById(user.getEmail()); // notice
        return true;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isEmpty())
            throw new UsernameNotFoundException("Not found");
        return user.get();
    }
}
