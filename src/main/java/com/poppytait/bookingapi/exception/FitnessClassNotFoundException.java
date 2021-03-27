package com.poppytait.bookingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FitnessClassNotFoundException extends Exception {
    public FitnessClassNotFoundException(Long id) {
        super("Fitness class with id " + id + " not found");
    }
}
