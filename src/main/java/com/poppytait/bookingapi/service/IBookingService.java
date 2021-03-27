package com.poppytait.bookingapi.service;

import com.poppytait.bookingapi.exception.DuplicateBookingException;
import com.poppytait.bookingapi.exception.FitnessClassNotFoundException;
import com.poppytait.bookingapi.exception.UserNotFoundException;
import com.poppytait.bookingapi.model.Booking;

import java.util.List;

public interface IBookingService {
    Booking addBooking(Long userId, Long fitnessClassId) throws FitnessClassNotFoundException, UserNotFoundException, DuplicateBookingException;
    List<Booking> getBookings(Long userId) throws UserNotFoundException;
}
