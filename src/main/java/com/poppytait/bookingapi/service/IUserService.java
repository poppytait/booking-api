package com.poppytait.bookingapi.service;

import com.poppytait.bookingapi.exception.UserNotFoundException;
import com.poppytait.bookingapi.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService {
    User findById(Long id) throws UserNotFoundException;

    User findByUsername(String username) throws UsernameNotFoundException;
}
