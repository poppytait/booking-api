package com.poppytait.bookingapi.service;

import com.poppytait.bookingapi.exception.DuplicateBookingException;
import com.poppytait.bookingapi.exception.FitnessClassNotFoundException;
import com.poppytait.bookingapi.exception.UserNotFoundException;
import com.poppytait.bookingapi.model.Booking;

public interface IBookingService {
    Booking addBooking(Long userId, Long fitnessClassId) throws FitnessClassNotFoundException, UserNotFoundException, DuplicateBookingException;
}
