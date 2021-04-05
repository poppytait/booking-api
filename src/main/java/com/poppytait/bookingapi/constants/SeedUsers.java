package com.poppytait.bookingapi.constants;

import com.poppytait.bookingapi.model.User;
import com.poppytait.bookingapi.security.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SeedUsers {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder(10);

    public static final User KYLE = new User("kyle", PASSWORD_ENCODER.encode("password"), UserRole.CUSTOMER);
    public static final User LINDA = new User("linda", PASSWORD_ENCODER.encode("password"), UserRole.INSTRUCTOR);
}
