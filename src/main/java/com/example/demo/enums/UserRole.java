package com.example.demo.enums;

public enum UserRole {
    ADMIN("role_admin"),
    USER("role_user");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
