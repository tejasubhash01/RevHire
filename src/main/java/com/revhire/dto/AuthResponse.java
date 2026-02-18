package com.revhire.dto;

import com.revhire.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String email;
    private Role role;
    private String token; 
}
