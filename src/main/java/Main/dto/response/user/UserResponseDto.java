package Main.dto.response.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String email;
    private String fullname;
    private String status;
    private String role;

}
