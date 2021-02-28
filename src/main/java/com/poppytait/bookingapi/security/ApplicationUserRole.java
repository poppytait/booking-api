package com.poppytait.bookingapi.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.poppytait.bookingapi.security.ApplicationUserPermission.FITNESS_CLASS_READ;
import static com.poppytait.bookingapi.security.ApplicationUserPermission.FITNESS_CLASS_WRITE;

public enum ApplicationUserRole {
    CUSTOMER(new HashSet<>(Collections.singletonList(FITNESS_CLASS_READ))),
    INSTRUCTOR(new HashSet<>(Arrays.asList(FITNESS_CLASS_READ, FITNESS_CLASS_WRITE))),
    ;

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
