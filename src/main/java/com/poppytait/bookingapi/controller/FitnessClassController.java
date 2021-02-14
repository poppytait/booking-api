package com.poppytait.bookingapi.controller;

import com.poppytait.bookingapi.exception.ErrorResponse;
import com.poppytait.bookingapi.exception.FitnessClassNotFoundException;
import com.poppytait.bookingapi.model.FitnessClass;
import com.poppytait.bookingapi.service.IFitnessClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("classes")
public class FitnessClassController {

    @Autowired
    private IFitnessClassService service;


    @GetMapping
    public List<FitnessClass> findFitnessClasses() {
        return service.findAll();
    }

    @PostMapping
    public FitnessClass addFitnessClass(@RequestBody FitnessClass fitnessClass) {
        return service.add(fitnessClass);
    }

    @DeleteMapping(value = "/{id}")
    public Long deleteFitnessClass(@PathVariable Long id) throws FitnessClassNotFoundException {
        return service.delete(id);
    }

    @ExceptionHandler(FitnessClassNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleFitnessClassNotFoundException(FitnessClassNotFoundException exception) {
        return new ErrorResponse(NOT_FOUND, exception.getMessage());
    }
}