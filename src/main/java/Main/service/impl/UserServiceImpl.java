package Main.service.impl;

import Main.dto.response.user.UserResponseDto;
import Main.entity.User;
import Main.exception.UserNotFoundException;
import Main.repository.UserRepository;
import Main.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        UserResponseDto userResponseDto = new UserResponseDto();

        BeanUtils.copyProperties(user, userResponseDto);

        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> getUsers(String email) {

        List<User> users = userRepository.findByEmailContaining(email);

        return users.stream()
                .map(this::convertToUserDto)
                .toList();
    }

    private UserResponseDto convertToUserDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFullname(user.getFullname());
        dto.setStatus(user.getStatus());
        dto.setRole(user.getRole());
        return dto;
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }

    @Override
    public UserResponseDto blockUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setStatus("BLOCK");
        User updatedUser = userRepository.save(user);

        return convertToUserDto(updatedUser);
    }

    @Override
    public UserResponseDto unblockUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setStatus("ACTIVE");
        User updatedUser = userRepository.save(user);

        return convertToUserDto(updatedUser);
    }

}
