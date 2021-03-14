package com.poppytait.bookingapi.repository;

import com.poppytait.bookingapi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
