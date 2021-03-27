package com.poppytait.bookingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateBookingException extends Exception {
    public DuplicateBookingException(Long userId, Long fitnessClassId) {
        super("User with id " + userId + " already booked class with id " + fitnessClassId);
    }
}
