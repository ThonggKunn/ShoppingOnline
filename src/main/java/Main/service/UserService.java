package Main.service;

import Main.dto.response.user.UserResponseDto;

import java.util.List;

public interface UserService {

    // User function
    UserResponseDto getUserById(Long id);

    // Admin function
    List<UserResponseDto> getUsers(String email);

    void deleteUserById(Long id);

    UserResponseDto blockUser(Long id);

    UserResponseDto unblockUser(Long id);

}
