package Main.service.impl;

import Main.dto.request.auth.UserLoginRequestDto;
import Main.dto.request.auth.UserRegisterRequestDto;
import Main.dto.response.auth.UserLoginResponseDto;
import Main.dto.response.auth.UserRegisterResponseDto;
import Main.entity.User;
import Main.repository.UserRepository;
import Main.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto request) {
        // Simple validation - find user by email
        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            return new UserLoginResponseDto(null, null, null, null,
                    "Email không tồn tại trong hệ thống", false);
        }

        // Checking password using BCrypt
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new UserLoginResponseDto(null, null, null, null,
                    "Mật khẩu không chính xác", false);
        }

        // Login success
        return UserLoginResponseDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .fullname(user.getFullname())
                .role(user.getRole())
                .message("Đăng nhập thành công")
                .success(true)
                .build();
    }

    @Override
    public UserRegisterResponseDto register(UserRegisterRequestDto request) {
        // Checking if email exist
        User existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser != null) {
            return new UserRegisterResponseDto(null, null, null,
                    "Email đã được sử dụng", false);
        }

        // Create new user
        User user = new User();
        BeanUtils.copyProperties(request, user);

        // Encrypt password using BCrypt
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Set default values
        if (user.getStatus() == null) {
            user.setStatus("ACTIVE");
        }
        if (user.getRole() == null) {
            user.setRole("USER");
        }
        user.setCreatedDate(new Date());

        User savedUser = userRepository.save(user);

        return UserRegisterResponseDto.builder()
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .fullname(savedUser.getFullname())
                .message("Đăng ký thành công")
                .success(true)
                .build();
    }

}
