package com.hms.controller;

import com.hms.dto.ApiResponse;
import com.hms.dto.DashboardStatsResponse;
import com.hms.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public ApiResponse<DashboardStatsResponse> getDashboardStats() {
        return ApiResponse.success("Dashboard statistics fetched successfully", dashboardService.getStats());
    }
}
