package service;

import dto.request.auth.UserLoginRequestDto;
import dto.request.auth.UserRegisterRequestDto;
import dto.response.auth.UserLoginResponseDto;
import dto.response.auth.UserRegisterResponseDto;

public interface AuthService {

    UserLoginResponseDto login(UserLoginRequestDto request);

    UserRegisterResponseDto register(UserRegisterRequestDto request);
}
