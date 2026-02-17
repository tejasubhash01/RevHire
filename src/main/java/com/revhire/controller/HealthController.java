package com.revhire.controller;

import com.revhire.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public ApiResponse<Map<String, String>> health() {
        return ApiResponse.ok("UP", Map.of("status", "UP"));
    }

    @GetMapping("/api/info")
    public ApiResponse<Map<String, String>> info() {
        return ApiResponse.ok("RevHire Backend", Map.of("app", "revhire", "version", "0.0.1"));
    }
}
