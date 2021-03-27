package com.poppytait.bookingapi.repository;

import com.poppytait.bookingapi.model.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
}
