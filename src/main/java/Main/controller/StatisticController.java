package Main.controller;

import Main.constant.UrlConstant;
import Main.dto.request.statistic.DateRangeRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Main.service.StatisticService;

@RestController
@RequestMapping(UrlConstant.API_BASE_V1)
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping(UrlConstant.GET_STATS_REVENUE)
    public ResponseEntity<Object> getRevenueStats(@RequestBody(required = false) DateRangeRequestDto request) {
        checkAdminAccess();
        return ResponseEntity.ok(statisticService.getRevenueStatistics(request));
    }

    // Helper method to check admin access (placeholder)
    private void checkAdminAccess() {
        // Placeholder implementation
        // Will be replaced with proper admin role check when Spring Security is added
        // Could throw AccessDeniedException if not admin
    }

}
