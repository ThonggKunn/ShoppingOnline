package controller;

import constant.UrlConstant;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstant.API_BASE_V1)
public class AuthController {

    @GetMapping(UrlConstant.LOGIN)
    public Object login(@RequestBody UserLoginRequestDto request) {
        return request; // Return request body
    }

    @PostMapping(UrlConstant.REGISTER)
    public Object register(@RequestBody UserRegisterRequestDto request) {
        return request; // Return request body
    }
}
