package com.revhire.service.impl;

import com.revhire.dto.AuthResponse;
import com.revhire.dto.LoginRequest;
import com.revhire.dto.RegisterRequest;
import com.revhire.entity.User;
import com.revhire.exception.BadRequestException;
import com.revhire.exception.UnauthorizedException;
import com.revhire.repository.UserRepository;
import com.revhire.security.JwtService;
import com.revhire.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already registered");
        }

        User user = User.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .active(true)
                .build();

        userRepository.save(user);

        // Optional: generate token on register
        String token = jwtService.generateToken(
                user.getEmail(),
                Map.of("role", user.getRole().name())
        );

        return new AuthResponse(user.getEmail(), user.getRole(), token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

        if (!user.isActive()) {
            throw new UnauthorizedException("Account is disabled");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                Map.of("role", user.getRole().name())
        );

        return new AuthResponse(user.getEmail(), user.getRole(), token);
    }
}
