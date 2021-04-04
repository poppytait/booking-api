package com.poppytait.bookingapi.service;

import com.poppytait.bookingapi.exception.BookingNotFoundException;
import com.poppytait.bookingapi.exception.DuplicateBookingException;
import com.poppytait.bookingapi.exception.UserNotFoundException;
import com.poppytait.bookingapi.model.Booking;
import com.poppytait.bookingapi.model.FitnessClass;
import com.poppytait.bookingapi.model.User;
import com.poppytait.bookingapi.repository.IBookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.poppytait.bookingapi.security.UserRole.CUSTOMER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    IFitnessClassService fitnessClassService;
    @Mock
    IUserService userService;
    @Mock
    IBookingRepository bookingRepository;

    @InjectMocks
    BookingService service;

    Instant instant = Instant.now();
    FitnessClass fitnessClass = new FitnessClass(2L, "Body Pump", "Janine", instant, instant, "weights room", 12);
    User user = new User(1L, "benny", "password", CUSTOMER);
    Booking booking = new Booking(3L, fitnessClass, user);

    @Test
    void shouldAddBooking() throws Exception {
        when(userService.findById(1L)).thenReturn(user); // expectations
        when(fitnessClassService.findById(2L)).thenReturn(fitnessClass);
        when(bookingRepository.save(any(Booking.class))).thenReturn(new Booking(fitnessClass, user));

        Booking actualBooking = service.addBooking(1L, 2L); // invocation

        assertEquals(booking.getFitnessClass(), actualBooking.getFitnessClass());
        assertEquals(booking.getUser(), actualBooking.getUser());
    }

    @Test
    void shouldThrowExceptionWhenDuplicateBooking() throws Exception {
        when(userService.findById(1L)).thenReturn(user); // expectations
        when(fitnessClassService.findById(2L)).thenReturn(fitnessClass);
        when(bookingRepository.save(any(Booking.class))).thenThrow(new DataIntegrityViolationException(""));

        assertThrows(DuplicateBookingException.class, () -> { //invocation & assertion
            service.addBooking(1L, 2L);
        });
    }

    @Test
    void shouldGetBookings() throws UserNotFoundException {
        List<Booking> bookings = new ArrayList<Booking>();
        bookings.add(booking);

        when(userService.findById(1L)).thenReturn(user);
        when(bookingRepository.findByUserId(1L)).thenReturn(bookings);

        List<Booking> actualBookings = service.getBookings(1L);

        assertEquals(bookings, actualBookings);
    }

    @Test
    void shouldCancelBooking() throws BookingNotFoundException {
        when(bookingRepository.findById(3L)).thenReturn(Optional.of(booking));

        Long actualId = service.cancelBooking(3L);

        assertEquals(booking.getId(), actualId);
    }

}