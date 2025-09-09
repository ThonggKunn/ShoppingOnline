package Main.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDto {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email is not in correct format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password must contain at least 1 lowercase letter, 1 uppercase letter and 1 number")
    private String password;

    @NotBlank(message = "Name cannot be black")
    @Size(min = 2, max = 100, message = "Name must be between 6 and 50 characters")
    private String fullname;

    private String status;

    private String role;

}
