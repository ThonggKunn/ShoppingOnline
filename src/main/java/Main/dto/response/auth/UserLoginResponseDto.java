package Main.dto.response.auth;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDto {

    private Long userId;
    private String email;
    private String fullname;
    private String role;
    private String message;
    private boolean success;

}
