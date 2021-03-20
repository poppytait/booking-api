package com.poppytait.bookingapi.service;

import com.poppytait.bookingapi.exception.FitnessClassNotFoundException;
import com.poppytait.bookingapi.exception.UserNotFoundException;
import com.poppytait.bookingapi.model.Booking;
import com.poppytait.bookingapi.model.FitnessClass;
import com.poppytait.bookingapi.model.User;
import com.poppytait.bookingapi.repository.IBookingRepository;
import com.poppytait.bookingapi.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements IBookingService {
    private IBookingRepository bookingRepository;
    private IUserRepository userRepository;
    private FitnessClassService fitnessClassService;

    public BookingService(IBookingRepository bookingRepository, IUserRepository userRepository, FitnessClassService fitnessClassService){
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.fitnessClassService = fitnessClassService;
    }

    @Override
    public Booking addBooking(Long userId, Long fitnessClassId) throws FitnessClassNotFoundException, UserNotFoundException {
        User user = findUserById(userId);
        FitnessClass fitnessClass = fitnessClassService.findById(fitnessClassId);

        Booking booking = new Booking(fitnessClass, user);

        return bookingRepository.save(booking);
    }

    public User findUserById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found."));
    }
}
