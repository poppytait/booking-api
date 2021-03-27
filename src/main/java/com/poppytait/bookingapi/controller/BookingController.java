package com.poppytait.bookingapi.controller;

import com.poppytait.bookingapi.exception.DuplicateBookingException;
import com.poppytait.bookingapi.exception.FitnessClassNotFoundException;
import com.poppytait.bookingapi.exception.UserNotFoundException;
import com.poppytait.bookingapi.model.Booking;
import com.poppytait.bookingapi.service.IBookingService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("booking")

public class BookingController {

    private final IBookingService service;

    public BookingController(IBookingService service) {
        this.service = service;
    }

    @PostMapping("/{userId}/{fitnessClassId}")
    public Booking addBooking(@PathVariable Long userId, @PathVariable Long fitnessClassId) throws FitnessClassNotFoundException, UserNotFoundException, DuplicateBookingException {
        return service.addBooking(userId, fitnessClassId);
    }
}
