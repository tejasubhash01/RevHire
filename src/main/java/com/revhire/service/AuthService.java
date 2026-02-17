package com.revhire.service;

import com.revhire.dto.AuthResponse;
import com.revhire.dto.LoginRequest;
import com.revhire.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
