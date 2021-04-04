package com.poppytait.bookingapi.service;

import com.poppytait.bookingapi.exception.UserNotFoundException;
import com.poppytait.bookingapi.model.User;
import com.poppytait.bookingapi.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static com.poppytait.bookingapi.security.UserRole.CUSTOMER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    IUserRepository repository;

    @InjectMocks
    UserService service;

    User user = new User(1L, "benny", "password", CUSTOMER);

    @Test
    void shouldFindById() throws Exception {
        when(repository.findById(2L)).thenReturn(Optional.of(user));

        User actualUser = service.findById(2L);

        assertEquals(user, actualUser);
    }

    @Test
    void shouldThrowExceptionWhenCannotFindById() throws Exception {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            service.findById(2L);
        });
    }

    @Test
    void shouldFindByUsername() throws Exception {
        when(repository.findUserByUsername("benny")).thenReturn(Optional.of(user));

        User actualUser = service.findByUsername("benny");

        assertEquals(user, actualUser);
    }

    @Test
    void shouldThrowExceptionWhenCannotFindUserByUsername(){
        when(repository.findUserByUsername("benny")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            service.findByUsername("benny");
        });
    }



}