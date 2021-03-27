package com.poppytait.bookingapi.security;

public enum ApplicationUserPermission {
    FITNESS_CLASS_READ("fitness-class:read"),
    FITNESS_CLASS_WRITE("fitness-class:write"),
    BOOKING_READ("booking:read"),
    BOOKING_WRITE("booking:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
