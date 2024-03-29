package com.poppytait.bookingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookingNotFoundException extends Exception {
    public BookingNotFoundException(Long id) {
        super("Booking with id " + id + " not found");
    }
}
