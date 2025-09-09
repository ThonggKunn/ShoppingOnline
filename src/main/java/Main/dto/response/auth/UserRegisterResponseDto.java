package Main.dto.response.auth;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponseDto {

    private Long userId;
    private String email;
    private String fullname;
    private String message;
    private boolean success;

}
