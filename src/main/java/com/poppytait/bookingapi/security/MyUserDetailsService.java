package com.poppytait.bookingapi.security;

import com.poppytait.bookingapi.model.User;
import com.poppytait.bookingapi.service.IUserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final IUserService userService;

    public MyUserDetailsService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return new MyUserDetails(user);
    }
}
