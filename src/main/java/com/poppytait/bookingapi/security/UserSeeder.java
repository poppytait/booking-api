package com.poppytait.bookingapi.security;

import com.poppytait.bookingapi.model.User;
import com.poppytait.bookingapi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class UserSeeder implements ApplicationListener<ApplicationReadyEvent> {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    IUserRepository userRepository;

    UserSeeder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createUserWithRole("beyonce", "password", UserRole.CUSTOMER);
        createUserWithRole("linda", "password", UserRole.INSTRUCTOR);
    }

    private void createUserWithRole(String username, String password, UserRole role){
        if(userRepository.findUserByUsername(username).isEmpty()){
            User user = new User(username, passwordEncoder.encode(password), role);
            userRepository.save(user);
        }
    }
}