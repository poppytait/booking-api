package com.poppytait.bookingapi.service;

import com.poppytait.bookingapi.exception.DuplicateBookingException;
import com.poppytait.bookingapi.exception.FitnessClassNotFoundException;
import com.poppytait.bookingapi.exception.UserNotFoundException;
import com.poppytait.bookingapi.model.Booking;
import com.poppytait.bookingapi.model.FitnessClass;
import com.poppytait.bookingapi.model.User;
import com.poppytait.bookingapi.repository.IBookingRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements IBookingService {
    private IBookingRepository bookingRepository;
    private IUserService userService;
    private IFitnessClassService fitnessClassService;

    public BookingService(IBookingRepository bookingRepository, IUserService userService, IFitnessClassService fitnessClassService){
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.fitnessClassService = fitnessClassService;
    }

    @Override
    public Booking addBooking(Long userId, Long fitnessClassId) throws FitnessClassNotFoundException, UserNotFoundException, DuplicateBookingException {
        User user = userService.findById(userId);
        FitnessClass fitnessClass = fitnessClassService.findById(fitnessClassId);

        Booking booking = new Booking(fitnessClass, user);

        try {
            return bookingRepository.save(booking);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateBookingException(userId, fitnessClassId);
        }
    }
}
