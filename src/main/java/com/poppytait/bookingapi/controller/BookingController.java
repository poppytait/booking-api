package com.poppytait.bookingapi.controller;

import com.poppytait.bookingapi.exception.DuplicateBookingException;
import com.poppytait.bookingapi.exception.FitnessClassNotFoundException;
import com.poppytait.bookingapi.exception.UserNotFoundException;
import com.poppytait.bookingapi.model.Booking;
import com.poppytait.bookingapi.service.IBookingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("booking")

public class BookingController {

    private final IBookingService service;

    public BookingController(IBookingService service) {
        this.service = service;
    }

    @PostMapping("/{userId}/{fitnessClassId}")
    @PreAuthorize("hasAuthority('booking:write')")
    public Booking addBooking(@PathVariable Long userId, @PathVariable Long fitnessClassId) throws FitnessClassNotFoundException, UserNotFoundException, DuplicateBookingException {
        return service.addBooking(userId, fitnessClassId);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('booking:read')")
    public List<Booking> getBookings(@PathVariable Long userId) throws UserNotFoundException {
        return service.getBookings(userId);
    }
}
