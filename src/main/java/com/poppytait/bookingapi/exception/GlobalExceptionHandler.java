package com.poppytait.bookingapi.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(FitnessClassNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleFitnessClassNotFoundException(FitnessClassNotFoundException exception) {
        return new ErrorResponse(NOT_FOUND, exception.getMessage());
    }
}
