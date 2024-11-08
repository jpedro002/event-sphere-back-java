package com.eventsphere.event_sphere.modules.user;

public enum RoleEnum {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}