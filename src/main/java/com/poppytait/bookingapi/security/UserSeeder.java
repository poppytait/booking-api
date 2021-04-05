package com.poppytait.bookingapi.security;

import com.poppytait.bookingapi.model.User;
import com.poppytait.bookingapi.repository.IUserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import static com.poppytait.bookingapi.constants.SeedUsers.KYLE;
import static com.poppytait.bookingapi.constants.SeedUsers.LINDA;

@Component
class UserSeeder implements ApplicationListener<ApplicationReadyEvent> {

    private final IUserRepository userRepository;

    UserSeeder(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createUserWithRole(KYLE);
        createUserWithRole(LINDA);
    }

    private void createUserWithRole(User user){
        if(userRepository.findUserByUsername(user.getUsername()).isEmpty()){
            userRepository.save(user);
        }
    }
}