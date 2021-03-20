package com.poppytait.bookingapi.repository;

import com.poppytait.bookingapi.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface IBookingRepository extends CrudRepository<Booking, Long> {

}
