package com.poppytait.bookingapi.controller;

import com.poppytait.bookingapi.model.FitnessClass;
import com.poppytait.bookingapi.service.IFitnessClassService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FitnessClassController {

    private IFitnessClassService service;

    public FitnessClassController(IFitnessClassService service) {
        this.service = service;
    }

    @GetMapping(value = "/classes")
    public List<FitnessClass> findFitnessClasses() {
        return service.findAll();
    }
}