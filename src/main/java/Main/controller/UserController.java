package Main.controller;

import Main.constant.UrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Main.service.UserService;

@RestController
@RequestMapping(UrlConstant.API_BASE_V1)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(UrlConstant.USER_PROFILE)
    public ResponseEntity<Object> getUserProfile() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    // Helper method to get current user ID (placeholder)
    public static Long getCurrentUserId() {
        // Placeholder implementation
        // Will be replaced with SecurityContextHolder implementation when Spring
        // Security is added
        return 2L;
    }

}
