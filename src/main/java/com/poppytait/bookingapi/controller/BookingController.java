package com.poppytait.bookingapi.controller;

import com.poppytait.bookingapi.exception.DuplicateBookingException;
import com.poppytait.bookingapi.exception.FitnessClassNotFoundException;
import com.poppytait.bookingapi.exception.UserNotFoundException;
import com.poppytait.bookingapi.model.Booking;
import com.poppytait.bookingapi.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("booking")

public class BookingController {

    @Autowired
    private IBookingService service;

    @PostMapping("/{userId}/{fitnessClassId}")
    public Booking addBooking(@PathVariable Long userId, @PathVariable Long fitnessClassId) throws FitnessClassNotFoundException, UserNotFoundException, DuplicateBookingException {
        return service.addBooking(userId, fitnessClassId);
    }
}
