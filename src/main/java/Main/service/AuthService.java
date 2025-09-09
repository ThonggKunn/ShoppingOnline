package Main.service;

import Main.dto.request.auth.UserLoginRequestDto;
import Main.dto.request.auth.UserRegisterRequestDto;
import Main.dto.response.auth.UserLoginResponseDto;
import Main.dto.response.auth.UserRegisterResponseDto;

public interface AuthService {

    UserLoginResponseDto login(UserLoginRequestDto request);

    UserRegisterResponseDto register(UserRegisterRequestDto request);
}
