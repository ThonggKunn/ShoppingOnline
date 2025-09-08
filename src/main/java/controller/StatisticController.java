package controller;

import constant.UrlConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlConstant.API_BASE_V1)
public class StatisticController {

    @GetMapping(UrlConstant.GET_STATS_REVENUE)
    public Object getRevenueStats(@RequestBody(required = false) DateRangeRequestDto request) {
        return request; // Return request body if provided, or null if not
    }

    // Helper method to check admin access (placeholder)
    private void checkAdminAccess() {
        // Placeholder implementation
        // Will be replaced with proper admin role check when Spring Security is added
        // Could throw AccessDeniedException if not admin
    }

}
