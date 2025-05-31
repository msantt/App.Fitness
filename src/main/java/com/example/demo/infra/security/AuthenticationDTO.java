package com.example.demo.infra.security;

public record AuthenticationDTO(String email, String senha) {

    public AuthenticationDTO {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
    }
}
