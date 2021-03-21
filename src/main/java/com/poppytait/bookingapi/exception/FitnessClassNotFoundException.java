package com.poppytait.bookingapi.exception;

public class FitnessClassNotFoundException extends Exception {
    public FitnessClassNotFoundException(Long id) {
        super("Fitness class with id: " + id + " not found");
    }
}
