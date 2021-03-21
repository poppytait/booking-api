package com.poppytait.bookingapi.exception;

public class DuplicateBookingException extends Exception {
    public DuplicateBookingException(Long userId, Long fitnessClassId) {
        super("User with id " + userId + " already booked class with id " + fitnessClassId);
    }
}
