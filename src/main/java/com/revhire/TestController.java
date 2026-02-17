package com.revhire;

import com.revhire.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public ApiResponse<String> test() {
        return ApiResponse.ok("OK", "Protected endpoint working");
    }
}
