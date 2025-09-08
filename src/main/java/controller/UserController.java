package controller;

import constant.UrlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlConstant.API_BASE_V1)
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(UrlConstant.USER_PROFILE)
    public Object getUserProfile() {
        return null; // No parameters
    }

    // Helper method to get current user ID (placeholder)
    public static Long getCurrentUserId() {
        // Placeholder implementation
        // Will be replaced with SecurityContextHolder implementation when Spring Security is added
        return 1L;
    }

}
