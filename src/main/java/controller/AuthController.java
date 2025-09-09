package controller;

import constant.UrlConstant;
import dto.request.auth.UserLoginRequestDto;
import dto.request.auth.UserRegisterRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AuthService;

@RestController
@RequestMapping(UrlConstant.API_BASE_V1)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(UrlConstant.LOGIN)
    public ResponseEntity<Object> login(@Valid @RequestBody UserLoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(UrlConstant.REGISTER)
    public ResponseEntity<Object> register(@Valid @RequestBody UserRegisterRequestDto request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
