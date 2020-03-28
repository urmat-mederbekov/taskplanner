package kg.attractor.taskplanner.dto;

import kg.attractor.taskplanner.model.User;
import lombok.*;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Data
public class UserDTO {
    public static UserDTO from(User user){
        return builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    private String username;
    private String email;
    private String password;
}
