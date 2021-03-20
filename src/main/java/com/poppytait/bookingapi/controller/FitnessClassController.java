package com.poppytait.bookingapi.controller;

import com.poppytait.bookingapi.exception.FitnessClassNotFoundException;
import com.poppytait.bookingapi.model.FitnessClass;
import com.poppytait.bookingapi.service.IFitnessClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("classes")
public class FitnessClassController {

    @Autowired
    private IFitnessClassService service;

    @GetMapping
    @PreAuthorize("hasAuthority('fitness-class:read')")
    public List<FitnessClass> findFitnessClasses() {
        return service.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('fitness-class:write')")
    public FitnessClass addFitnessClass(@RequestBody FitnessClass fitnessClass) {
        return service.add(fitnessClass);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('fitness-class:write')")
    public Long deleteFitnessClass(@PathVariable Long id) throws FitnessClassNotFoundException {
        return service.delete(id);
    }
}